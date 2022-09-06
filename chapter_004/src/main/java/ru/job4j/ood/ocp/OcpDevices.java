package ru.job4j.ood.ocp;

import java.util.List;

public class OcpDevices {
    private static class Device {

        private String name;
        public Device(String name) {
            this.name = name;
        }
        public String soundPhone() {
            return "soundOfPhone";
        }

        public String soundPComputer() {
            return "soundOfComputer";
        }

        public String sizePhone() {
            return "sizeOfPhone";
        }
        public String sizeComputer() {
            return "sizeOfComputer";
        }
        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        List<Device> devices = List.of(new Device("Computer"), new Device("Phone"), new Device("Computer"));
        devices.forEach(el -> {
            if (el.getName().equals("Computer")) {
                System.out.println(el.sizeComputer() + " " + el.soundPComputer());
            } else if (el.getName().equals("Phone")) {
                System.out.println(el.sizePhone() + " " + el.soundPhone());
            }
        });
    }
}
