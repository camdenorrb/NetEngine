package com.noyhillel.networkengine.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Noy on 02/05/2014.
 * @author Noy Hillel
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class NewNetCommandException extends NetException {
    /**
     * Private field The Error Message.
     */
    private final String message;
    /**
     * Private field, The Error Type from our Enum which we created.
     */
    private final ErrorType errorType;

    /**
     * This enumerator represents all the Error Types.
     */
    public enum ErrorType {
        /**
         * SPECIAL Error Type, for example: If an exceptions occurred, It will be a 'SPECIAL' Error Type.
         */
        SPECIAL,
        /**
         * PERMISSION Error Type, for example: If the Player executing the command does not have permission, It will be a 'PERMISSION' Error.
         */
        PERMISSION,
        /**
         * Few Arguments Error Type, for example: If the Command Sender doesn't provide enough arguments, It will be a 'FEW_ARGUMENTS' Error.
         */
        FEW_ARGUMENTS,
        /**
         * Many Arguments Error Type, for example: If the Command Sender provides too many arguments, It will be a 'MANY_ARGUMENTS' Error.
         */
        MANY_ARGUMENTS,
        /**
         * NULL Error Type, for example: If the sender provides something which is null, It will be a 'NULL' Error.
         */
        NULL
    }
}