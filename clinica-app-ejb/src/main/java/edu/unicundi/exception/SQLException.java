/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author familia manrique
 */
@ApplicationException(rollback = true)
public class SQLException extends Exception {
    private static final long serialVersionUID = 1L;

    public SQLException(String mensaje) {
        super(mensaje);
    }
}
