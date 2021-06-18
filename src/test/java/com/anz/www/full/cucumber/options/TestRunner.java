package com.anz.www.full.cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/anz/www/full/features/HomeLoanBorrowEstimate.feature", glue="com.anz.www.full.stepDefinations")
public class TestRunner {

}
