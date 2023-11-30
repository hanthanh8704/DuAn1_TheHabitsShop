/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;



/**
 *
 * @author ThanhDat
 */
public class GetIdSPCTService11 {

    public VoucherService spct = new VoucherService();

    public String getIDVoucher(String ma) {
        return spct.getIdVoucher(ma);
    }
}
