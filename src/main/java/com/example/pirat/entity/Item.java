package com.example.pirat.entity;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    private String id;
    private String asset_id;
    private int skin_id;
    private int bot_id;
    private int price;
    private String float_id;
    private double float_value;
    private int tradehold;
    private int paint_seed;
    private int paint_index;
    private List<Sticker> stickers;
    private int sticker_counter;
    private int ss;
    private int status;
    private String name;
    private String class_id;
    private int skin_status;
    private int discount;
    private int suggested_price;
    private int category_id;
    private List<Integer> collection_id;
    private int exterior_id;
    private int paint_id;
    private int type_id;
    private int typesub_id;
    private int quality_id;
    private String bot_steam_id;

}

class Sticker {
    private String name;
    private int slot;
    private String type;
    private int skin_id;
    private String class_id;
    private int skin_status;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSkin_id() {
        return skin_id;
    }

    public void setSkin_id(int skin_id) {
        this.skin_id = skin_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public int getSkin_status() {
        return skin_status;
    }

    public void setSkin_status(int skin_status) {
        this.skin_status = skin_status;
    }
}
