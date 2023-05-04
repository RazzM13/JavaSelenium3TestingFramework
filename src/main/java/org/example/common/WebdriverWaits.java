package org.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverWaits {

    /**
     * Reference:
     * https://stackoverflow.com/questions/25062969/testing-angularjs-with-selenium
     */
    public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return driver -> {
            var javaScriptExecutor = (JavascriptExecutor) driver;
            String javaScriptExecutionResult = javaScriptExecutor.executeScript(
                    """
                          try {
                            if (document.readyState !== 'complete') {
                              return false; // Page not loaded yet
                            }
                            if (window.jQuery) {
                              if (window.jQuery.active) {
                                return false;
                              } else if (window.jQuery.ajax && window.jQuery.ajax.active) {
                                return false;
                              }
                            }
                            if (window.angular) {
                              if (!window.qa) {
                                // Used to track the render cycle finish after loading is complete
                                window.qa = {
                                  doneRendering: false
                                };
                              }
                              // Get the angular injector for this app (change element if necessary)
                              var injector = window.angular.element('body').injector();
                              // Store providers to use for these checks
                              var $rootScope = injector.get('$rootScope');
                              var $http = injector.get('$http');
                              var $timeout = injector.get('$timeout');
                              // Check if digest
                              if ($rootScope.$$phase === '$apply' || $rootScope.$$phase === '$digest' || $http.pendingRequests.length !== 0) {
                                window.qa.doneRendering = false;
                                return false; // Angular digesting or loading data
                              }
                              if (!window.qa.doneRendering) {
                                // Set timeout to mark angular rendering as finished
                                $timeout(function() {
                                  window.qa.doneRendering = true;
                                }, 1000);
                                return false;
                              }
                            }
                            return true;
                          } catch (ex) {
                            return false;
                          }
                        """
            ).toString();
            return Boolean.valueOf(javaScriptExecutionResult);
        };
    }

    public static void waitForAngular(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10, 300);
        wait.until(WebdriverWaits.angularHasFinishedProcessing());
    }

    public static void waitForElementBy(WebDriver webDriver, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10, 300);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
