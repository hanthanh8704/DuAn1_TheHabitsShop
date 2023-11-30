/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raven.classinterface;

import java.util.List;

/**
 *
 * @author Ninh Than Thanh
 */
public interface TheHabitShop<EntityType,KeyType> {
    public abstract List<EntityType> getAll();
    public abstract int insert(EntityType entity);
    public abstract int update(EntityType entity, KeyType id);
    public abstract int delete(KeyType id);
    public abstract EntityType getID(KeyType id);
    public abstract List<EntityType> getSql(String sql, Object... args);
}
