package oop.ex6.main.exceptions.scopeExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VarNameExistsInBlockException extends SjavaException {
    String errorMessage = "cannot assign this name to variable - name already exists in block";
}