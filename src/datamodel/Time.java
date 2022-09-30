package datamodel;

import exception.InvalidTimeException;

public class Time implements Comparable<Time>{
    private final int day, hr, mm;

    public Time(String time) throws InvalidTimeException {
        int[] splitTime = parse(time);
        this.day = splitTime[0];
        this.hr = splitTime[1];
        this.mm = splitTime[2];
        validate();
    }
    public Time(int day, int hr, int mm) throws InvalidTimeException {
        this.day = day;
        this.hr = hr;
        this.mm = mm;
        validate();
    }

    private int[] parse(String time){
        String[] s = time.split(" ");
        int[] arr = new int[s.length];
        for(int i=0;i<s.length;i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        return arr;
    }

    private void validate() throws InvalidTimeException {
        if(day<0)
            throw new InvalidTimeException();
        else if(hr<0||hr>23||mm<0||mm>59)
            throw new InvalidTimeException();
    }


    @Override
    public int compareTo(Time o) {
        if(o.day<this.day)
            return 1;
        else if(o.day>this.day)
            return -1;
        if(o.hr<this.hr)
            return 1;
        else if(o.hr>this.hr)
            return -1;
        else{
            return Integer.compare(o.mm,this.mm);
        }
    }

    public String toString(){
        String hour = (hr<10)?"0"+hr:Integer.toString(hr);
        String min = (mm<10)?"0"+mm:Integer.toString(mm);
        return "Day : "+day+" Time : "+hour+":"+min;
    }

    public int getDay() {
        return day;
    }

    public int getHr() {
        return hr;
    }

    public int getMm() {
        return mm;
    }
}
