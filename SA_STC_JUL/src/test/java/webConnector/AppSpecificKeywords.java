package webConnector;

import util.BaseUtil;

public class AppSpecificKeywords extends GenericKeywords {

	public AppSpecificKeywords(BaseUtil base) {
		super(base);
	}

	public void getToSignInPage() {
		try {
			base.log.info("Clicking Sign In link");			
			
			base.keyword.waitUntilElementIsVisible("signinLink");
			base.keyword.click("signinLink");
			
			base.keyword.waitUntilPageLoadsComplelety();
			
			//Check email text is located
			base.keyword.waitUntilElementIsVisible("signInheaderTitleLabel");
		} catch (Exception e) {
			base.log.info(e.getMessage());
		}
	}
	public void getToSearchPage() {
		try {
			//base.log.info("Clicking Sign In link");			
			
			base.keyword.waitUntilElementIsVisible("searchLocationText");
			//base.keyword.click("signinLink");
			
			base.keyword.waitUntilPageLoadsComplelety();
			
			//Check email text is located
			//base.keyword.waitUntilElementIsVisible("signInheaderTitleLabel");
		} catch (Exception e) {
			base.log.info(e.getMessage());
		}
	}



}
