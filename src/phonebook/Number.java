package phonebook;

public class Number {
    private String phoneNumber;
    private int count;

    public Number(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.count = 0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(Number phoneNumber) {
        return Integer.compare(this.count, phoneNumber.getCount());
    }
}
