package dados;

public class Data {
    private int day, month, year;
    private final int[] MONTHS_30_DAYS = {4, 6, 9, 11};

    public Data(int day, int month, int year) {
        this.setDay(day);
        this.setYear(year);
        this.setMonth(month);
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) throws IllegalArgumentException {
        if (day < 1 | day > 31) {
            throw new IllegalArgumentException("invalid date");
        }
        this.day = day;
    }

    public void incDay() {
        if (this.getDay() == 30 && Operadores.in(this.getMonth(), this.MONTHS_30_DAYS)) {
            this.setDay(1);
            this.incMonth();
        } else if (this.getDay() == 28 && this.getMonth() == 2) {
            this.setDay(1);
            this.incMonth();
        } else {
            this.setDay(this.day + 1);
        }
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) throws IllegalArgumentException {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("invalid date");
        }
        if (Operadores.in(month, this.MONTHS_30_DAYS) && this.getDay() > 30) {
            throw new IllegalArgumentException("invalid date");
        }
        if (month == 2 && this.getDay() > 29) {
            throw new IllegalArgumentException("invalid date");
        }
        if (!isLeapYear(this.getYear()) && this.getDay() > 28) {
            throw new IllegalArgumentException("invalid date");
        }
        this.month = month;
    }

    public void incMonth() {
        if (this.getMonth() == 12) {
            this.setMonth(1);
            this.incYear();
        } else if (this.getDay() == 31 && (this.getMonth() + 1 == 2 || Operadores.in((this.getMonth() + 1) % 12, this.MONTHS_30_DAYS))) {
            this.setDay(1);
            this.setMonth(this.month + 1);
        } else {
            this.setMonth(this.month + 1);
        }
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) throws IllegalArgumentException {
        if (year < 1) {
            throw new IllegalArgumentException("invalid date");
        }
        this.year = year;
    }

    public void incYear() {
        this.setYear(this.year + 1);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static Data fromString(String date) throws IllegalArgumentException {
        String[] splittedDate = date.split("/");
        if (splittedDate.length != 3) {
            throw new IllegalArgumentException("invalid date");
        }
        return new Data(Integer.parseInt(splittedDate[0]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[2]));
    }

    public String toString() {
        return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
    }
}
