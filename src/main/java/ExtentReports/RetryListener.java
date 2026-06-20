package ExtentReports;
import org.testng.annotations.ITestAnnotation;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        // Check if a retry analyzer class is already set
    	Class<? extends IRetryAnalyzer> retryClass = annotation.getRetryAnalyzerClass();
    	if (retryClass == null) {
    	    annotation.setRetryAnalyzer(RetryAnalyzer.class);
    	}
}
}