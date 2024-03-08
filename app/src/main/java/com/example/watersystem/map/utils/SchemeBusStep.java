package com.example.watersystem.map.utils;

import com.amap.api.services.route.BusStep;

public class SchemeBusStep extends BusStep {

    private boolean isWalk = false;
    private boolean isBus = false;
    private boolean isRailway = false;
    private boolean isTaxi = false;
    private boolean isStart = false;
    private boolean isEnd = false;
    private boolean arrowExpend = false;

    public SchemeBusStep(BusStep step) {
        if (step != null) {
            this.setBusLine(step.getBusLine());
            this.setWalk(step.getWalk());
            this.setRailway(step.getRailway());
            this.setTaxi(step.getTaxi());
        }
    }

    public boolean isArrowExpend() {
        return arrowExpend;
    }

    public void setArrowExpend(boolean arrowExpend) {
        this.arrowExpend = arrowExpend;
    }

    public boolean isWalk() {
        return isWalk;
    }

    public void setWalk(boolean isWalk) {
        this.isWalk = isWalk;
    }

    public boolean isBus() {
        return isBus;
    }

    public void setBus(boolean isBus) {
        this.isBus = isBus;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean isRailway() {
        return isRailway;
    }

    public boolean isTaxi() {
        return isTaxi;
    }

    public void setRailway(boolean israilway) {
        this.isRailway = israilway;
    }

    public void setTaxi(boolean istaxi) {
        this.isTaxi = istaxi;
    }


}
