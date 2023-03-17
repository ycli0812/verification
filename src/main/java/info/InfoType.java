package info;

public enum InfoType {
    /**
     * Ordinary output information. Mainly used to hint the progress of the verification.
     */
    INFO,

    /**
     * Used to report issues that will not terminate the verification.
     */
    WARNING,

    /**
     * Used to report serious problems that terminate the verification.
     */
    ERROR
}
