package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count =0;
	int trymax = 2;
	
//	 the below method will be invoked if any test script fails and tries to rerun
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<trymax)
		{
			count++;
			return true;
		}
		return false;
	}

}
