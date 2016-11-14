require("RPostgreSQL")
require('bReeze')

table_name <- 'grid_450'
height = 120

driver <- dbDriver("PostgreSQL", max.con = 250)
connection <- dbConnect(driver, dbname="geoalternar", user= "glassfish", password="V3Mu02GR", host="localhost")

args <- commandArgs(TRUE)

latitude = args[1]
longitude = args[2]

path <- '/tmp/'

bins1 = c(4,8,12,18,25)
bins2 = c(1,2,3,4)

select <- "SELECT timewind AS timestamp, speed, direction"
from <- paste("FROM ", table_name," natural join windinterpolation120_2010_2014a ", sep='')
where <- "WHERE"
coordinates <- paste('grid_450.latitude_3857=',latitude,' AND grid_450.longitude_3857=',longitude, sep='')
time_constraint <- "AND timewind < '2015-01-01' order by timewind"
sql <- paste(select, from, where, coordinates, time_constraint)
print(sql)
timeserie <- dbGetQuery(connection, sql)

if(dim(timeserie)[1]!=0){
  set050 <- set(height=height,  v.avg=timeserie$speed, dir.avg=timeserie$direction)
  ts <- as.POSIXlt(timeserie$timestamp, "America/Bogota")
  metmast <- mast(timestamp=ts, set050=set050)
  if (max(timeserie$speed) > 4){
    bins = bins1
  } else {
    bins = bins2
  }
  freq <- frequency(mast=metmast, v.set=1, bins=bins, print=F)
  png(filename=paste0(path,'wr120m_',longitude,'_',latitude,'.png'))
  plot(freq)
  dev.off()
  
  wb <- weibull(mast=metmast, v.set=1, print=F)
  png(filename=paste0(path,'wb120m_',longitude,'_',latitude,'.png'))
  plot(wb, show.ak=T)
  dev.off()}
print('done')