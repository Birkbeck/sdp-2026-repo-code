package creational.builder.pc;

import java.util.Objects;

// builder for immutable objects
public class Computer {

    //required parameters
    private final String HDD;
    private final String RAM;

    //optional parameters
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;


    private Computer(Builder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer{" +
            "HDD='" + HDD + '\'' +
            ", RAM='" + RAM + '\'' +
            ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
            ", isBluetoothEnabled=" + isBluetoothEnabled +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer computer)) return false;
        return isGraphicsCardEnabled() == computer.isGraphicsCardEnabled() && isBluetoothEnabled() == computer.isBluetoothEnabled() && getHDD().equals(computer.getHDD()) && getRAM().equals(computer.getRAM());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHDD(), getRAM(), isGraphicsCardEnabled(), isBluetoothEnabled());
    }

    //Builder Class
    public static class Builder {

        // required parameters
        private final String HDD;
        private String RAM;

        // optional parameters
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        public Builder(String hdd) {
            this.HDD = hdd;
        }

        public Builder(String hdd, String ram) {
            this.HDD = hdd;
            this.RAM = ram;
        }

        public Builder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }

        @Override
        public String toString() {
            return "ComputerBuilder{" +
                "HDD='" + HDD + '\'' +
                ", RAM='" + RAM + '\'' +
                ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
                ", isBluetoothEnabled=" + isBluetoothEnabled +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Builder that)) return false;
            return isGraphicsCardEnabled == that.isGraphicsCardEnabled && isBluetoothEnabled == that.isBluetoothEnabled && HDD.equals(that.HDD) && RAM.equals(that.RAM);
        }

        @Override
        public int hashCode() {
            return Objects.hash(HDD, RAM, isGraphicsCardEnabled, isBluetoothEnabled);
        }
    }
}