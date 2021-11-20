package com.example.software_i.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts= FXCollections.observableArrayList();
    private int id, stock, min, max;
    private String name;
    private double price;

    public Product() {

    }

    /**
     *
     * @param id
     * @param stock
     * @param min
     * @param max
     * @param name
     * @param price
     */
    public Product(int id, int stock, int min, int max, String name, double price) {
        this.id = id;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.price = price;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @param part
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     *
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
           return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     *
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
