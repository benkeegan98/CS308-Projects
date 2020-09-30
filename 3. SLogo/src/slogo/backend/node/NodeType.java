package slogo.backend.node;


import java.util.ResourceBundle;

/**
 * Defines the types of inputs that are accepted by the parser. The inputs are mapped to their
 * respective regular expressions using the Syntax resource file
 */
public enum NodeType {
    COMMAND(),
    CONSTANT(),
    VARIABLE(),
    LISTSTART(),
    LISTEND(),
    GROUPSTART(),
    GROUPEND(),
    COMMENT();

    private String syntax;

    private ResourceBundle rb = ResourceBundle.getBundle("resources.languages/Syntax");

    NodeType() {
        this.syntax = rb.getString(this.name());
    }

    /**
     * @return the regular expression syntax of the nodeType
     */
    public String getSyntax() {
        return this.syntax;
    }
}

