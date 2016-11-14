Select 
	m.latitude_3857, m.longitude_3857,m.value_point 
from 
	(grid_450 natural join  maps_wind_20) m 
where m.latitude_3857=235800 and m.longitude_3857=-8634600 and m.tag_time='General'

SELECT * from maps_wind_120a 

--
SELECT m.* 
	FROM(
	(SELECT
		latitude_3857,longitude_3857,grid450_id,windspeed
	FROM
		(grid_450 natural join avghourwindspeed)
	where 
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='0') h0
		JOIN	(SELECT grid450_id,windspeed
		FROM 
		(grid_450 natural join avghourwindspeed) 
		where 
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='1') h1
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='2') h2
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='3') h3
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='4') h4
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='5') h5
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='6') h6
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='7') h7
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where 
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='8') h8
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='9') h9
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='10') h2
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='11') h3
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='12') h4
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='13') h5
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='14') h6
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='15') h7
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where 
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='16') h8
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='17') h9
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='18') h2
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='19') h3
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed 
		FROM 
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='20') h4
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='21') h5
		using (grid450_id)
		JOIN (SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='22') h6
		using	(grid450_id)
		JOIN	(SELECT grid450_id,windspeed
		FROM
		(grid_450 natural join avghourwindspeed)
		where
		latitude_3857=-8796600 and longitude_3857=182250 and hourday='23') h7
		using (grid450_id)
) m

--SELECT * from avghourwindspeed limit 10



---
update maps_cuencas C set name_watershed=(
SELECT 
	cuencas.name_watershed 
from 
	cuencas join maps_cuencas using(value_point)
where 
	value_point>0 and maps_cuencas.grid450_id=C.grid450_id
order by 
	grid450_id,value_point) 


---
UPDATE maps_cloud Q set tag_time =(select 'General' from maps_cloud P where tag_time ='Caudal' and Q.grid450_id=P.grid450_id)

UPDATE maps_cuencas Q set grid450_id =(select grid450_id from grid_450 P where Q.latitude_3857=P.latitude_3857 and Q.longitude_3857=P.longitude_3857)