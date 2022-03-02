window.onload = function () {
    var pureCoverage = false;
      // if this is just a coverage or a group of them, disable a few items,
      // and default to jpeg format
      var format = 'image/png';
      var bounds = [-8794075, 39620,
                    -8580222, 298660];
      var mousePositionControl = new ol.control.MousePosition({
        className: 'custom-mouse-position',
        target: document.getElementById('location'),
        coordinateFormat: ol.coordinate.createStringXY(5),
        undefinedHTML: '&nbsp;'
      });
      ////Division
      
      ////Cabeceras municipales
      var cabeceras = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format,
                   'VERSION': '1.1.1',  
                STYLES: '',
                LAYERS: 'MapRiver:cabeceras3857',
          }
        })
      });
      //Limites cuencas
      var limitescuenca = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format,
                   'VERSION': '1.1.1',  
                STYLES: '',
                LAYERS: 'MapRiver:cuencas3857',
          }
        })
      });
      //drenajeDoble
      var drenajedoble = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format,
                   'VERSION': '1.1.1',  
                STYLES: '',
                LAYERS: 'MapRiver:drenajeDoble',
          }
        })
      });
      //CUENCAS
      var cuenca = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format,
                   'VERSION': '1.1.1',  
                STYLES: '',
                LAYERS: 'MapRiver:Cuencas450',
          }
        })
      });
      var cuenca2 = new ol.layer.Tile({
        visible: true,
        source: new ol.source.TileWMS({
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format, 
                   'VERSION': '1.1.1',
                   tiled: true,
                STYLES: '',
                LAYERS: 'MapRiver:Cuencas450',
          }
        })
      });
      //estaciones
      var untiled = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format,
                   'VERSION': '1.1.1',  
                STYLES: '',
                LAYERS: 'MapRiver:estaciones',
          }
        })
      });
      var tiled = new ol.layer.Tile({
        visible: false,
        source: new ol.source.TileWMS({
          url: 'http://190.254.4.40:8080/geoserver/MapRiver/wms',
          params: {'FORMAT': format, 
                   'VERSION': '1.1.1',
                   tiled: true,
                STYLES: '',
                LAYERS: 'MapRiver:estaciones',
          }
        })
      });
      var projection = new ol.proj.Projection({
          code: 'EPSG:3857',
          units: 'm',
          axisOrientation: 'neu'
      });
      /*var map = new ol.Map({
        controls: ol.control.defaults({
          attribution: false
        }).extend([mousePositionControl]),
        target: 'map',
        layers: [new ol.layer.Tile({
                     source: new ol.source.MapQuest({layer: 'osm'})
                    }),cuenca,untiled,tiled,cabeceras,limitescuenca],
        view: new ol.View({
           projection: projection
        })
      });*/
    ///***
        var map = new ol.Map({
        controls: ol.control.defaults({
          attribution: false
        }).extend([mousePositionControl]),
        target: 'map',
        layers: [new ol.layer.Tile({
                source: new ol.source.Stamen({
                    layer: 'terrain'
                }),
                name: 'Terrain'
            }),cuenca,untiled,tiled,cabeceras,limitescuenca],
        view: new ol.View({
           projection: projection
        })
      });
      ///***
      map.getView().on('change:resolution', function(evt) {
        var resolution = evt.target.get('resolution');
        var units = map.getView().getProjection().getUnits();
        var dpi = 25.4 / 0.28;
        var mpu = ol.proj.METERS_PER_UNIT[units];
        var scale = resolution * mpu * 39.37 * dpi;
        if (scale >= 9500 && scale <= 950000) {
          scale = Math.round(scale / 1000) + "K";
        } else if (scale >= 950000) {
          scale = Math.round(scale / 1000000) + "M";
        } else {
          scale = Math.round(scale);
        }
      });
      map.getView().fitExtent(bounds, map.getSize());
      map.on('singleclick', function(evt) {
          
        document.getElementById('nodelist').innerHTML = "Loading... please wait...";
        var view = map.getView();
        var viewResolution = view.getResolution();
        var source = untiled.get('visible') ? untiled.getSource() : tiled.getSource();
        var url = source.getGetFeatureInfoUrl(
                evt.coordinate, viewResolution, view.getProjection(),
                {'INFO_FORMAT': 'text/html', 'FEATURE_COUNT': 50}
        );
        //alert(evt.coordinate);
        document.getElementById('frmlatlon:latitudeCap').value = evt.coordinate[0].toFixed(0);
        document.getElementById('frmlatlon:longitudeCap').value = evt.coordinate[1].toFixed(0);
        var firstProjection = 'EPSG:3857';
        var secondProjection = 'EPSG:4326';
        var result = proj4(firstProjection, secondProjection, [evt.coordinate[0], evt.coordinate[1]]);

        document.getElementById('frmlatlon:lat4326').value = result[0].toFixed(6);
        document.getElementById('frmlatlon:lon4326').value = result[1].toFixed(6);

        if (url) {
            //document.getElementById('nodelist').innerHTML = '<iframe seamless  style="width: 99%;" src="' + url + '"></iframe>';
            document.getElementById('nodelist').innerHTML = '<iframe  src="' + url + '"></iframe>';
        }
        var fireOnThis = document.getElementById("frmlatlon:btAjax");
        var evObj = document.createEvent('Event');
        evObj.initEvent('click', true, true);
        fireOnThis.dispatchEvent(evObj);
    });
}




