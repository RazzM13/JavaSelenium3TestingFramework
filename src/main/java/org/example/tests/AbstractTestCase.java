package org.example.tests;

import org.example.common.TestDependencies;

abstract public class AbstractTestCase {

    public Boolean hasFailed = false;
    public Boolean hasFaulted = false;
    public String errorMessage = "";
    public String Description;
    protected TestDependencies testDependencies;

    public AbstractTestCase(String description, TestDependencies testDependencies) {
        this.Description = description;
        this.testDependencies = testDependencies;
    }

    protected void before() {}

    abstract protected void script();

    protected void after() {}

    public String createErrorMessage(Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.getMessage());

        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (StackTraceElement stackTraceElement: stackTraceElements) {
            stringBuilder.append(String.format(
                    "%s(%s:%s)",
                    stackTraceElement.getClass(),
                    stackTraceElement.getFileName(),
                    stackTraceElement.getLineNumber()
            ));
        }

        return stringBuilder.toString();
    }

    public void run() {
        try {
            this.before();
            this.script();
            this.after();
        }
        catch (AssertionError e) {
            this.hasFailed = true;
            this.errorMessage = this.createErrorMessage(e);
        }
        catch (Exception e) {
            this.hasFaulted = true;
            this.errorMessage = this.createErrorMessage(e);
        }
    }

}
