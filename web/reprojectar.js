function reproject3857() {
    var lat=document.getElementById('latepsg4326').value;
    var lon=document.getElementById('lonepsg4326').value;
    ////////REPOYECCION
    //var firstProjection = 'EPSG:4326';
    //var secondProjection = 'EPSG:3857';
    var firstProjection = 'EPSG:4326';
    var secondProjection = 'EPSG:3857';
    var result = proj4(firstProjection, secondProjection, [lon,lat]);
    alert(result);
    document.getElementById('latepsg3857').value = result[1];
    document.getElementById('lonepsg3857').value = result[0];
}
//219231.23326357
//-8603104.20697656
//−8605814,221580