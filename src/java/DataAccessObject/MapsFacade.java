/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import Entidad.Maps;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author edixred
 */
@Stateless
public class MapsFacade extends AbstractFacade<Maps> {
    @PersistenceContext(unitName = "GeoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapsFacade() {
        super(Maps.class);
    }
    
    public Object[] getByCoordenate(int latitude, int longitude, String map, int type) {
        String typemap = "";
        if (type == 50) {
            typemap = "maps_wind_50";
        }
        if (type == 20) {
            typemap = "maps_wind_20";
        }
        if (type == 120) {
            typemap = "maps_wind_120";
        }
        if (type == 2) {
            typemap = "maps_biomass";
        }
        if (type == 3) {
            typemap = "maps_solar";
        }
        if (type == 4) {
            typemap = "maps_sun_modis";
        }
        if (type == 5) {
            typemap = "maps_cuencas";
        }
        if (type == 6) {
            typemap = "maps_cloud";
        }
        try {
            Query q = getEntityManager().createNativeQuery("Select m.latitude_3857, m.longitude_3857,m.value_point,m.height from (grid_450 natural join  " + typemap + ") m where m.latitude_3857=? and m.longitude_3857=? and m.tag_time=?");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            q.setParameter(3, map);
            
            return (Object[]) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Object[] getHistoryMonths(int latitude, int longitude, int type) {
        String typemap = "";
        if (type == 50) {
            typemap = "maps_wind_50";
        }
        if (type == 20) {
            typemap = "maps_wind_20";
        }
        if (type == 120) {
            typemap = "maps_wind_120";
        }
        if (type == 2) {
            typemap = "maps_biomass";
        }
        if (type == 3) {
            typemap = "maps_solar";
        }
        if (type == 4) {
            typemap = "maps_sun_modis";
        }
        if (type == 6) {
            typemap = "maps_cloud";
        }
        try {

            Query qmonths = getEntityManager().createNativeQuery("SELECT m.* FROM( "
                    + " (SELECT grid450_id,value_point AS january FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='January') a1 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS february FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='February') a2 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS march FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='March') a3 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS april FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='April') a4 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS May FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='May') a5 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS June FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='June') a6 "
                    + "NATURAL JOIN   "
                    + " (SELECT grid450_id,value_point AS July FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='July') a7 "
                    + "NATURAL JOIN  "
                    + "(SELECT grid450_id,value_point AS August FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='August') a8 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS September FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='September') a9 "
                    + "NATURAL JOIN  "
                    + " (SELECT grid450_id,value_point AS october FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='October') a10 "
                    + "NATURAL JOIN   "
                    + " (SELECT grid450_id,value_point AS november FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='November') a11  "
                    + "NATURAL JOIN "
                    + "  (SELECT grid450_id,value_point AS december FROM grid_450 natural join  " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='December') a12 "
                    + ") as m"
            );

            qmonths.setParameter(1, latitude);
            qmonths.setParameter(2, longitude);
            qmonths.setParameter(3, latitude);
            qmonths.setParameter(4, longitude);
            qmonths.setParameter(5, latitude);
            qmonths.setParameter(6, longitude);
            qmonths.setParameter(7, latitude);
            qmonths.setParameter(8, longitude);
            qmonths.setParameter(9, latitude);
            qmonths.setParameter(10, longitude);
            qmonths.setParameter(11, latitude);
            qmonths.setParameter(12, longitude);
            qmonths.setParameter(13, latitude);
            qmonths.setParameter(14, longitude);
            qmonths.setParameter(15, latitude);
            qmonths.setParameter(16, longitude);
            qmonths.setParameter(17, latitude);
            qmonths.setParameter(18, longitude);
            qmonths.setParameter(19, latitude);
            qmonths.setParameter(20, longitude);
            qmonths.setParameter(21, latitude);
            qmonths.setParameter(22, longitude);
            qmonths.setParameter(23, latitude);
            qmonths.setParameter(24, longitude);
            System.out.println("MONTHS OK");
            return (Object[]) qmonths.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public Object[] getHistoryYears(int latitude, int longitude, int type) {
      
        String typemap = "";
        if (type == 50) {
            typemap = "maps_wind_50";
        }
        if (type == 20) {
            typemap = "maps_wind_20";
        }
        if (type == 120) {
            typemap = "maps_wind_120";
        }
        if (type == 2) {
            typemap = "maps_biomass";
        }
        if (type == 3) {
            typemap = "maps_solar";
        }
        if (type == 4) {
            typemap = "maps_sun_modis";
        }
        String s="SELECT m.* FROM("
                    + " (SELECT grid450_id,value_point AS a2000 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2000') a1 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2001 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2001') a2 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2002 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2002') a3 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2003 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2003') a4 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2004 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2004') a5  "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2005 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2005') a6 "
                    + "NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2006 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2006') a7 "
                    + "NATURAL  JOIN "
                    + "(SELECT  	grid450_id,value_point AS a2007 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2007') a8 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2008 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2008') a9 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2009 FROM  	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2009') a10 "
                    + "NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2010 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2010') a11  "
                    + "NATURAL  JOIN  "
                    + "  (SELECT 	grid450_id,value_point AS a2011 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2011') a12 "
                    + "  NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2012 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2012') a13  "
                    + " NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2013 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2013') a14  "
                    + " NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2014 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2014') a15  "
                    + ") as m";
        try {
            Query qyears = getEntityManager().createNativeQuery(s
            );

            qyears.setParameter(1, latitude);
            qyears.setParameter(2, longitude);
            qyears.setParameter(3, latitude);
            qyears.setParameter(4, longitude);
            qyears.setParameter(5, latitude);
            qyears.setParameter(6, longitude);
            qyears.setParameter(7, latitude);
            qyears.setParameter(8, longitude);
            qyears.setParameter(9, latitude);
            qyears.setParameter(10, longitude);
            qyears.setParameter(11, latitude);
            qyears.setParameter(12, longitude);
            qyears.setParameter(13, latitude);
            qyears.setParameter(14, longitude);
            qyears.setParameter(15, latitude);
            qyears.setParameter(16, longitude);
            qyears.setParameter(17, latitude);
            qyears.setParameter(18, longitude);
            qyears.setParameter(19, latitude);
            qyears.setParameter(20, longitude);
            qyears.setParameter(21, latitude);
            qyears.setParameter(22, longitude);
            qyears.setParameter(23, latitude);
            qyears.setParameter(24, longitude);
            qyears.setParameter(25, latitude);
            qyears.setParameter(26, longitude);
            qyears.setParameter(27, latitude);
            qyears.setParameter(28, longitude);
            qyears.setParameter(29, latitude);
            qyears.setParameter(30, longitude);
            System.out.println("con:"+s);
            System.out.println("lon:"+longitude);
            System.out.println("lat:"+latitude);
            return (Object[]) qyears.getSingleResult();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
            return null;
        }
    }

    public Object[] getHistoryHours(int latitude, int longitude,int height) {
        String heigthmaphour = "";
        if (height == 50) {
            heigthmaphour = "avghourwindspeed";
        }
        if (height == 20) {
            heigthmaphour = "avghourwindspeed20";
        }
        if (height == 120) {
            heigthmaphour = "avghourwindspeed120";
        }
        try {
            Query qyears = getEntityManager().createNativeQuery("SELECT m.* \n"
                    + "	FROM(	\n"
                    + "	(SELECT 	\n"
                    + "	latitude_3857,longitude_3857,grid450_id,windspeed  	\n"
                    + "	FROM 		\n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='0') h0\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='1') h1\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='2') h2\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='3') h3\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='4') h4\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='5') h5\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='6') h6\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='7') h7\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='8') h8\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='9') h9\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='10') h10\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='11') h11\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='12') h12\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='13') h13\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='14') h14\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='15') h15\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='16') h16\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='17') h17\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='18') h18\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='19') h19\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='20') h20\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN	(SELECT grid450_id,windspeed  	\n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='21') h21\n"
                    + "	using (grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='22') h22\n"
                    + "	using	(grid450_id)\n"
                    + "	JOIN (SELECT grid450_id,windspeed \n"
                    + "	FROM \n"
                    + "	(grid_450 natural join "+heigthmaphour+") \n"
                    + "	where \n"
                    + "	latitude_3857=? and longitude_3857=? and hourday='23') h23\n"
                    + "	using	(grid450_id)\n"
                    + ") m"
            );

            qyears.setParameter(1, latitude);
            qyears.setParameter(2, longitude);
            qyears.setParameter(3, latitude);
            qyears.setParameter(4, longitude);
            qyears.setParameter(5, latitude);
            qyears.setParameter(6, longitude);
            qyears.setParameter(7, latitude);
            qyears.setParameter(8, longitude);
            qyears.setParameter(9, latitude);
            qyears.setParameter(10, longitude);
            qyears.setParameter(11, latitude);
            qyears.setParameter(12, longitude);
            qyears.setParameter(13, latitude);
            qyears.setParameter(14, longitude);
            qyears.setParameter(15, latitude);
            qyears.setParameter(16, longitude);
            qyears.setParameter(17, latitude);
            qyears.setParameter(18, longitude);
            qyears.setParameter(19, latitude);
            qyears.setParameter(20, longitude);
            qyears.setParameter(21, latitude);
            qyears.setParameter(22, longitude);
            qyears.setParameter(23, latitude);
            qyears.setParameter(24, longitude);
            qyears.setParameter(25, latitude);
            qyears.setParameter(26, longitude);
            qyears.setParameter(27, latitude);
            qyears.setParameter(28, longitude);
            qyears.setParameter(29, latitude);
            qyears.setParameter(30, longitude);
            qyears.setParameter(31, latitude);
            qyears.setParameter(32, longitude);
            qyears.setParameter(33, latitude);
            qyears.setParameter(34, longitude);
            qyears.setParameter(35, latitude);
            qyears.setParameter(36, longitude);
            qyears.setParameter(37, latitude);
            qyears.setParameter(38, longitude);
            qyears.setParameter(39, latitude);
            qyears.setParameter(40, longitude);
            qyears.setParameter(41, latitude);
            qyears.setParameter(42, longitude);
            qyears.setParameter(43, latitude);
            qyears.setParameter(44, longitude);
            qyears.setParameter(45, latitude);
            qyears.setParameter(46, longitude);
            qyears.setParameter(47, latitude);
            qyears.setParameter(48, longitude);
            return (Object[]) qyears.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    //MODIS AÑOS
    public Object[] getHistoryYearsModis(int latitude, int longitude, int type) {
        
        String typemap = "";
        if (type == 4) {
            typemap = "maps_sun_modis";
        }
        if (type == 6) {
            typemap = "maps_cloud";
        }
        String s="SELECT m.* FROM("
                    + " (SELECT grid450_id,value_point AS a2000 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2005') a1 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2006 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2006') a7 "
                    + "NATURAL  JOIN "
                    + "(SELECT  	grid450_id,value_point AS a2007 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2007') a8 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2008 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2008') a9 "
                    + "NATURAL  JOIN "
                    + " (SELECT 	grid450_id,value_point AS a2009 FROM  	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2009') a10 "
                    + "NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2010 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2010') a11  "
                    + "NATURAL  JOIN  "
                    + "  (SELECT 	grid450_id,value_point AS a2011 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2011') a12 "
                    + "  NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2012 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2012') a13  "
                    + " NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2013 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2013') a14  "
                    + " NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2014 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2014') a15  "
                    + " NATURAL  JOIN  "
                    + " (SELECT 	grid450_id,value_point AS a2015 FROM 	grid_450 natural join " + typemap + " where latitude_3857=? and longitude_3857=? and tag_time='2015') a16  "
                    + ") as m";
        try {
            Query qyears = getEntityManager().createNativeQuery(s);

            qyears.setParameter(1, latitude);
            qyears.setParameter(2, longitude);
            qyears.setParameter(3, latitude);
            qyears.setParameter(4, longitude);
            qyears.setParameter(5, latitude);
            qyears.setParameter(6, longitude);
            qyears.setParameter(7, latitude);
            qyears.setParameter(8, longitude);
            qyears.setParameter(9, latitude);
            qyears.setParameter(10, longitude);
            qyears.setParameter(11, latitude);
            qyears.setParameter(12, longitude);
            qyears.setParameter(13, latitude);
            qyears.setParameter(14, longitude);
            qyears.setParameter(15, latitude);
            qyears.setParameter(16, longitude);
            qyears.setParameter(17, latitude);
            qyears.setParameter(18, longitude);
            qyears.setParameter(19, latitude);
            qyears.setParameter(20, longitude);
            qyears.setParameter(21, latitude);
            qyears.setParameter(22, longitude);
            qyears.setParameter(23, latitude);
            qyears.setParameter(24, longitude);
            qyears.setParameter(25, latitude);
            qyears.setParameter(26, longitude);
            qyears.setParameter(27, latitude);
            qyears.setParameter(28, longitude);
            qyears.setParameter(29, latitude);
            qyears.setParameter(30, longitude);
            return (Object[]) qyears.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
