/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classinterface;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public abstract class EduSysDAO<EntityType, KeyType> {
    // Hàm abstract dùng để khai báo
    public abstract void insert(EntityType entity);
    
    public abstract void update(EntityType entity);
    
    public abstract void delete(KeyType id);
    
    public abstract List<EntityType> selectAll();
    
    public abstract EntityType selectById(KeyType id);
    
    public abstract List<EntityType> selectBySql(String sql, Object...args);
    
}
 