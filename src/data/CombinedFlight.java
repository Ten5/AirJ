package data;

import persistence.*;

/**
 *
 * @author AirJ
 */

public class CombinedFlight {
    public int flightCount;
    private String deptSilk,arrSilk,arrSpice,deptSpice,intermediate,totalTime,via,spiceFlightNo,silkFlightNo;
    final int ADDED_TIME = 150;
    
    public CombinedFlight(String source,int Day,int month, int passCount) {
        int i,j;
        deptSilk="";
        arrSilk="";
        arrSpice="";
        deptSpice="";
        intermediate="";
        totalTime="";
        via="";
        spiceFlightNo="";
        silkFlightNo="";
        String date = Day +"/"+month;
        
        SilkAirSchedule silkAir = new SilkAirSchedule();
        silkAir.getBookedFilghts(date.trim(), passCount);
        SpiceJetSchedule spiceJet = new SpiceJetSchedule();
        spiceJet.getBookedFilghts(date.trim(), passCount);
        
        for(i=0;i<spiceJet.size;i++) {
            if(spiceJet.flights[i].getSource().compareTo(source) == 0 && spiceJet.flights[i].getFrequency(Day)) { 
                for(j=0;j<silkAir.size;j++) { 
                    String silkSource=silkAir.flights[j].getSource();
                    int depTimeHr = silkAir.getHr(j,0);
                    int depTimeMin = silkAir.getMin(j,0);

                    String spiceDest = spiceJet.flights[i].getDestination();
                    spiceDest = spiceDest.trim();

                    int arrTimeHr = spiceJet.getHr(i,1);
                    int arrTimeMin = spiceJet.getMin(i,1);
                    if(spiceDest.compareTo(silkSource.substring(0,silkSource.lastIndexOf("(")-1).toUpperCase().trim())==0 ) { 
                        if(silkAir.flights[j].getFrequency(Day)) {
                        	int time = timediff(arrTimeHr, arrTimeMin, depTimeHr, depTimeMin, false); 
                            if(time >= 120 && time <= 360)
                                flightCount++;
                        }
                    }
                    /*
                     * The following else-if part is to check
                     * if the flight MI 475 which departs at 00:45
                     * is available or not.
                     */
                    if(silkAir.flights[j].getFrequency((Day+1)%7))
                    	if(silkAir.getHr(j,  0) < 6)
                    		flightCount++;
                }			   
            }
        }
    }
    
     public void setSilkFlightNo(String flightNo){
        silkFlightNo= flightNo;
    }
     public void setSpiceFlightNo(String flightNo){
        spiceFlightNo= flightNo;
    }
    public void setIntermediate(String inter){
        intermediate= inter;
    }
    public void setDuration(String time){
        totalTime= time;
    }
    public void setVia(String via){
        this.via = via;
    }
    public void setDeptSilk(String dep_time){
        this.deptSilk = dep_time;
    }
    public void setArrSilk(String arr_time){
        this.arrSilk= arr_time;
    }
     public void setDeptSpice(String dep_time){
        this.deptSpice = dep_time;
    }
    public void setArrSpice(String arr_time){
        this.arrSpice= arr_time;
    }
    public String getSilkFlightNo() {
        return(silkFlightNo);
    }
     public String getSpiceFlightNo() {
        return(spiceFlightNo);
    }
    public String getIntermediate() {
        return(intermediate);
    }
    public String getDuration() {
        return(totalTime);
    }
    public String getVia() {
        return(via );
    }
    public String getDeptSilk() {
        return(deptSilk);
    }
    public String getArrSilk() {
        return(arrSilk);
    }
     public String getDeptSpice() {
        return(deptSpice) ;
    }
    public String getArrSpice() {
       return(arrSpice);
    }
    
    public CombinedFlight[] combine(String source,int Day,int month, int passCount) {
        int i,j,totalTime,Count=0;
        String date = Day+"/"+month;
        SilkAirSchedule silkAir = new SilkAirSchedule();
        silkAir.getBookedFilghts(date, passCount);
        SpiceJetSchedule spiceJet = new SpiceJetSchedule();
        spiceJet.getBookedFilghts(date, passCount);
        
        int duration[]=new int[flightCount];
        CombinedFlight cf[]=new CombinedFlight[flightCount];
        for(i=0;i<flightCount;i++)
            cf[i]=new CombinedFlight(source,Day,month, passCount);
        
        for(i=0;i<spiceJet.size;i++) {
            if(spiceJet.flights[i].getSource().compareTo(source) == 0 && spiceJet.flights[i].getFrequency(Day)) { 
                for(j=0;j<silkAir.size;j++) {  
                    String silkSource=silkAir.flights[j].getSource(); 
                    int depTimeHr = silkAir.getHr(j,0);
                    int depTimeMin = silkAir.getMin(j,0);

                    String spiceDest = spiceJet.flights[i].getDestination();
                    spiceDest = spiceDest.trim();

                    int arrTimeHr = spiceJet.getHr(i,1);
                    int arrTimeMin = spiceJet.getMin(i,1);
                    
                    if(spiceDest.compareTo(silkSource.substring(0,silkSource.lastIndexOf("(")-1).toUpperCase().trim())==0 ) { 
                        if(silkAir.flights[j].getFrequency(Day)) {
                        	int time = timediff(arrTimeHr, arrTimeMin, depTimeHr, depTimeMin, false); 
                            if(time >= 120 && time <= 360) {
                                cf[Count].setDeptSpice(spiceJet.flights[i].getDepTime());
                                cf[Count].setSpiceFlightNo(spiceJet.flights[i].getFlightNo());
                                cf[Count].setArrSpice(spiceJet.flights[i].getArrTime());
                                cf[Count].setIntermediate(spiceDest);
                                if(spiceJet.flights[i].isHopping())
                                    cf[Count].setVia(spiceJet.flights[i].getVia());
                                else
                                    cf[Count].setVia("Direct");
                                
                                cf[Count].setDeptSilk(convert(silkAir.flights[j].getDepTime()));
                                cf[Count].setSilkFlightNo(silkAir.flights[j].getFlightNo());
                                cf[Count].setArrSilk(convert(silkAir.flights[j].getArrTime()));
                               
                                
                                totalTime = timediff(spiceJet.getHr(i, 0), spiceJet.getMin(i, 0), silkAir.getHr(j, 1), silkAir.getMin(j, 1), ((spiceJet.getHr(i, 0) < silkAir.getHr(j, 0)) ? true:false)) - ADDED_TIME;                                
                                duration[Count]=totalTime;
                                cf[Count].setDuration(Integer.toString(totalTime/60)+"hrs "+Integer.toString(totalTime%60)+ "mins");
                                Count++;                               
                            }
                        }
                        
                        /*
                         * The following else-if part is to check
                         * if the flight MI 475 which departs at 00:45
                         * is available or not.
                         */
                        if(silkAir.flights[j].getFrequency((Day+1)%7)) {
                        	if(silkAir.getHr(j,  0) < 6) {
                        		int time = timediff(arrTimeHr, arrTimeMin, depTimeHr, depTimeMin, true); 
                                if(time >= 120 && time <= 360) {                            
                                    cf[Count].setDeptSpice(spiceJet.flights[i].getDepTime());
                                    cf[Count].setSpiceFlightNo(spiceJet.flights[i].getFlightNo());
                                    cf[Count].setArrSpice(spiceJet.flights[i].getArrTime());
                                    cf[Count].setIntermediate(spiceDest);
                                    if(spiceJet.flights[i].isHopping())
                                        cf[Count].setVia(spiceJet.flights[i].getVia());
                                    else
                                        cf[Count].setVia("Direct");
                                    
                                    cf[Count].setDeptSilk(convert(silkAir.flights[j].getDepTime()));
                                    cf[Count].setSilkFlightNo(silkAir.flights[j].getFlightNo());
                                    cf[Count].setArrSilk(convert(silkAir.flights[j].getArrTime()));
                                    
                                    totalTime = timediff(spiceJet.getHr(i, 0), spiceJet.getMin(i, 0), silkAir.getHr(j, 1), silkAir.getMin(j, 1), true);
                                    duration[Count]=totalTime;
                                    cf[Count].setDuration(Integer.toString(totalTime/60)+"hrs "+Integer.toString(totalTime%60)+ "mins");
                                    Count++;                             
                                }
                        	}
                        }
                    }
                }			   
            }
        }
        sort(duration,cf);
        return cf;
    }
    
    public static int timediff(int start_hr, int start_min, int end_hr, int end_min, boolean flag) {
        int diff;
        if(flag)
            diff=((end_hr+24)-start_hr)*60;
        else
            diff=(end_hr-start_hr)*60;
        return(diff+(end_min-start_min));
    }
    
    String convert(String time) {
        String t;
        String hr=time.substring(0, 2);
        String min=time.substring(2, 4);
        if(Integer.parseInt(hr)>12)
            t=Integer.parseInt(hr)-12 + ":" + min + " PM"; 
        else
            t=hr+ ":" + min + " AM";
        if(time.contains("+"))
            t=t+" (+ 1)";
        return t; 
    }
    
    public void sort(int duration[],CombinedFlight cf[]) {
        int i,j,k,swap=1;
        
        CombinedFlight temp;
        for(i=0; i < flightCount && swap == 1; i++) {
            swap=0;
            for(j=0; j<flightCount-1; j++) {
                if(duration[j]>duration[j+1]) {
                    swap=1;
                    k=duration[j];
                    duration[j]=duration[j+1];
                    duration[j+1]=k;
                    
                       temp=cf[j];
                       cf[j]=cf[j+1];
                       cf[j+1]=temp;
                }
            }
        }
    }
}