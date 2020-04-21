package com.openmrs.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.openmrs.utils.TestDataProvider;



public class AnnotationTransformer implements IAnnotationTransformer{


	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
				annotation.setDataProvider("getData");								//sets the dataprovider to all the test methods
				annotation.setDataProviderClass(TestDataProvider.class);
				annotation.setRetryAnalyzer(RetryFailedTestCases.class); 			//sets the retry analyser for all the test cases
	}
}



