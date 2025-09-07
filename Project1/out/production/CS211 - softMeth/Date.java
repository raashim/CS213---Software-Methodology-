import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    int date;
    int[] daysFprMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int year, int month, int day) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    @Override
    public int compareTo(Date date2){
        if(this.year!=date2.year){
            return Integer.compare(this.year,date2.year);
        }
        if(this.month!=date2.month){
            return Integer.compare(this.month,date2.month);
        }
        return Integer.compare(this.day,date2.day);
        }

        @Override
     public boolean equals(Object anotherDate){
        if(this==anotherDate){
            return true;
        }
        if(anotherDate==null|| !(anotherDate instanceof Date)){
            return false;
        }
        Date newDate=(Date) anotherDate;
        return  year ==newDate.year && //checks if all 3 conditions are equal to each other after casting
                month == newDate.month &&
                day ==newDate.day;

     }

     @Override
     public String toString(){
       return String.format("%02d/%02d/%04d",month,day,year);
     }

    public boolean IsToday() {
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
        int monthNow = calendar.get(Calendar.MONTH) + 1;

        if (yearNow == this.year && dayNow == this.day && monthNow == this.month) {
            return true;
        }
        return false;

    }

    public boolean isWeekend() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        //setting the calendar date to the day that you're taking in
        calendar.set(Calendar.MONTH, this.month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, this.day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    public boolean withinSix() {
        Calendar calendar = Calendar.getInstance();
        Calendar newCal = Calendar.getInstance();

        newCal.add(Calendar.MONTH, 6);
        Calendar appDate = Calendar.getInstance();
        appDate.set(Calendar.YEAR, this.year);
        appDate.set(Calendar.MONTH, this.month - 1);
        appDate.set(Calendar.DAY_OF_MONTH, this.day);

        if (appDate.after(newCal)) {
            return false;
        }
        return true;
    }


        public boolean isBeforeToday() {
            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);
            int monthNow = calendar.get(Calendar.MONTH) + 1; //+1 bc it starts at 0
            int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

            if (this.year < yearNow) {
                return true;
            } else if (this.year == yearNow) {
                if (this.month < monthNow) {
                    return true;
                } else if (this.month == monthNow && this.day < dayNow) {
                    return true;
                }
            }
            return false;
        }

        public boolean isAfterToday(){
            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);
            int monthNow = calendar.get(Calendar.MONTH) + 1; //+1 bc it starts at 0
            int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

            if (this.year > yearNow) {
                return true;
            } else if (this.year == yearNow) {
                if (this.month > monthNow) {
                    return true;
                } else if (this.month == monthNow && this.day > dayNow) {
                    return true;
                }
            }
            return false;
        }


        //checks if the month is valid
  public boolean isValidMonth(){
      if(this.month<=12 && this.month>=1){
          return true;
      }
      return false;
  }

  public int daysInMonth(int month,int year){
        int maxDay;
      if(month==1){
          maxDay=daysFprMonth[0];
          return maxDay; }
      if(month==2 && isLeapYear(year)){
          maxDay=29;
          return maxDay; }
      if(month==2 && !isLeapYear(year)){
          maxDay=daysFprMonth[1];
          return maxDay; }
      if(month==3){
          maxDay=daysFprMonth[2];
          return maxDay; }
      if(month==4){
          maxDay=daysFprMonth[3];
          return maxDay; }
      if(month==5){
          maxDay=daysFprMonth[4];
          return maxDay; }
      if(month==6){
          maxDay=daysFprMonth[5];
          return maxDay; }
      if(month==7){
          maxDay=daysFprMonth[6];
          return maxDay; }
      if(month==8){
          maxDay=daysFprMonth[7];
          return maxDay; }
      if(month==9){
          maxDay=daysFprMonth[8];
          return maxDay; }
      if(month==10){
          maxDay=daysFprMonth[9];
          return maxDay; }
      if(month==11){
          maxDay=daysFprMonth[10];
          return maxDay; }
      if(month==12){
          maxDay=daysFprMonth[11];
          return maxDay; }
      return -1; }

  public boolean isLeapYear(int year) {
      if(year%QUATERCENTENNIAL==0){
          return true; //if divisble by 400 then leap year
      }
      else if(year%CENTENNIAL==0){
          return false;
      }
      else if(year%QUADRENNIAL==0){
          return true;
      }
      return false;
  }

  public boolean isValidDay() {
      if(this.day>31){
          return false;
      }
      int maxMonthDays=daysInMonth(this.month,this.year);
      if(this.day>=1 && this.day<=maxMonthDays){
          return true;
      }
      return false;
  }

  public static void main(String[]args){
          Date test1 = new Date(1989, 12, 32);
          boolean yes = test1.isValidDay();  // call the instance method
          System.out.println("Is the day valid? " + yes);

      Date test3 = new Date(2024, 9, 31);
      boolean yes3 = test3.isValidDay();  // call the instance method should return INVALID
      System.out.println("Is the day valid? " + yes3);


      Date test2 = new Date(2024, 11, 2);
      boolean yes1 = test2.isValidDay();  // call the instance method  should return VALID
      System.out.println("Is the day valid? " + yes1);


  }

}
