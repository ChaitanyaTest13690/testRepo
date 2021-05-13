/*package Libraries;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TableUtil extends AllConfigurations
{
	private static final Collection String = null;
	static int EC2_rowCounter=1;
	static int EC2Running_rowCounter=1;
	static int EC2_rowCounterSecurity=1;
	static int EC2Stop_rowCounter=1;
	static int SG_rowCounterSecurity=1;
	static int SG_rowCounterSecurityNextPage=1;
	static int SG_rowCounterSecurityNextPage1=1;
	static int SG_rowCounterSecurityNextPage2=1;
	static int VPC_rowCounter=1;
	static int Subnet_Counter=1;
	static int SecurityGroup_Counter=1;
	static int RT_Counter=1;
	static int VPC_rowCounterSecurity=1;
	static int AMI_rowCounterSecurity=1;
	static int NACL_rowCounterSecurity=1;
	static int NACLInbound_rowCounterSecurity=1;
	static int NACLOutbound_rowCounterSecurity=1;
	static int NACLSubnetAssociations_rowCounterSecurity=1;
	
	//Inbound
	static int SGBastonIB_Counter=1;
	static int SGGlusterIB_Counter=2;
	static int SGInfraIB_Counter=1;
	static int SGInfraIBSG_Counter=1;
	static int SGMasterLBIB_Counter=1;
	static int SGMasterSGIB_Counter=1;
	static int SGComputeIB_Counter=1;
	
	static int SGInbound_rowCounterSecurity=1;
	
	//Outbound
	static int SGBastonOB_Counter=1;
	static int SGGlusterOB_Counter=2;
	static int SGInfraLBOB_Counter=1;
	static int SGInfraSGOB_Counter=1;
	static int SGMasterLBOB_Counter=1;
	static int SGMasterSGOB_Counter=1;
	static int SGComputeOB_Counter=1;
	
	static int searchCounter=0;
	static int searchCounterSG=0;
	static int searchCounterSecurity=0;
	static int searchCounterVPC=0;
	static int searchCounterSubnet=0;
	static int searchCounterSecurityGroupIB=0;
	
	
	//RouteTable
	static int RTRoutesPublic=1;
	static int RTRoutesPrivate=1;
	static int RTSubnetAssociationsPublic=1;
	
	static int RTRouteUpperTab=3;
	static int RTSubnetAssociationsTab=2;
	static int RTSubnetAssociationsPrivate=1;
	
	WebDriver driver;
	WebDriverWait wait;
	
	//EC2
	By searchString = By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div[1]/div[3]/table/tbody/tr/td[1]/div/div/div/div[3]/div[1]/div/div[2]/input");
	By sortByNameASC = By.xpath("//div[text()='Name']");
	
	//VPC
	By searchStringVPC = By.xpath("//input[@placeholder='Search VPCs and their properties...']");

	//Subnet
	By searchStringSubnet = By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[4]/div/div[1]/div[2]/div/div[2]/div/div[3]/div[1]/div[3]/table/tbody/tr/td[1]/div/div/div/div[3]/div[1]/div/div[2]/input");
	
	//Security Groups
	By searchStringSecurityGroup = By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div[1]/div[3]/table/tbody/tr/td[1]/div/div/div/div[3]/div[1]/div/div[2]/input");
	By clickOnInbound = By.xpath("//div[text()='Inbound']");
	By clickOnInboundRules = By.xpath("//div[text()='Inbound Rules']");
	By clickOnOutboundRules = By.xpath("//div[text()='Outbound Rules']");
	By clickOnSubnetAssociations = By.xpath("//div[text()='Subnet associations']");
	
	//Actions click
	By clickOnActions = By.xpath("//button[@id='gwt-debug-menuButton']");
	By clickOnInstanceState = By.xpath("//div[@id='gwt-debug-menu-instance-state']");
	By clickOnStop = By.xpath("//div[@id='gwt-debug-action-stop-instances']");
	By clickOnStart = By.xpath("//div[contains(text(),'Start')]");
	By clickOnConfirmStop = By.xpath("//span[contains(text(),'Yes, Stop')]");
	By clickOnConfirmStart = By.xpath("//span[contains(text(),'Yes, Start')]");
	By clickOnRefresh = By.xpath("//img[@title='Refresh']");
	
	public TableUtil(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, 30);
	}
	
	public String getTableCellValueEC2(WebDriver driver,String colinstanceName, String colinstanceType, String colinstanceState, String colinstanceKey) throws InterruptedException
	{
		if (searchCounter==0)
		{
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounter++;
		}
		String InstanceName=null, InstanceType=null, InstanceState=null, InstanceKeyName=null, InstanceSecurityGroup=null, sActual=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div"));
		int instanceNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceName = driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_instanceName.equals("Name"))
			{
				instanceNamePosition=i;
				break;
			}
		}
		int instanceTypePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceType = driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceType.equals("Instance Type"))
			{
				instanceTypePosition=i;
				break;
			}
		}
		int instanceStatePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceState= driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceState.equals("Instance State"))
			{
				instanceStatePosition=i;
				break;
			}
		}
		int instanceKeyNamePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceKeyName= driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceKeyName.equals("Key Name"))
			{
				instanceKeyNamePosition=i;
				break;
			}
		}
		//Instance SecurityGroup Position:15
		System.out.println("Instance Security Group Position");
		int instanceSecurityGroupPosition = 0;
		for (int i=2;i<=colNames.size();i++)
		{
			String colName_instanceSecurityGroup= driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceSecurityGroup.equals("Security Groups"))
			{
				System.out.println("Instance Security Group Column Name:"+colName_instanceSecurityGroup);
				instanceSecurityGroupPosition=i;
				System.out.println("Instance SecurityGroup Position:"+instanceSecurityGroupPosition);
				break;
			}
		}
		//System.out.println("************************************************************************");
		
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));

		for (; EC2_rowCounter<= rowElement.size(); EC2_rowCounter++)
		{
			InstanceName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounter+"]/td)["+instanceNamePosition+"]")).getText();
			InstanceType = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounter+"]/td)["+instanceTypePosition+"]")).getText();
			InstanceState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounter+"]/td)["+instanceStatePosition+"]")).getText();
			InstanceKeyName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounter+"]/td)["+instanceKeyNamePosition+"]")).getText();
			//InstanceSecurityGroup = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounter+"]/td)["+instanceSecurityGroupPosition+"]")).getText();
			sActual = InstanceName + InstanceType + InstanceState + InstanceKeyName;
			//System.out.println("Act String:"+sActual);
			EC2_rowCounter++;
			break;
		}
		return sActual;
	}
	
	public String getTableCellValueEC2Running(WebDriver driver,String colinstanceName) throws InterruptedException
	{
		if (searchCounter==0)
		{
			String search="Running";
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchString));
			driver.findElement(searchString).clear();
			driver.findElement(searchString).sendKeys(search);
			Thread.sleep(2500);
			driver.findElement(searchString).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounter++;
		}
		
		String InstanceName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div"));
		int instanceNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceName = driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_instanceName.equals("Name"))
			{
				instanceNamePosition=i;
				break;
			}
		}
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));

		for (; EC2Running_rowCounter<= rowElement.size(); EC2Running_rowCounter++)
		{
			InstanceName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2Running_rowCounter+"]/td)["+instanceNamePosition+"]")).getText();
			
			if (InstanceName.equals(colinstanceName))
			{
				driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2Running_rowCounter+"]/td)["+instanceNamePosition+"]")).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnActions));
				driver.findElement(clickOnActions).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnInstanceState));
				driver.findElement(clickOnInstanceState).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnStop));
				driver.findElement(clickOnStop).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnConfirmStop));
				driver.findElement(clickOnConfirmStop).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnRefresh));
				driver.findElement(clickOnRefresh).click();
				Thread.sleep(2500);
				break;
			}
		}
		return null;
	}

	public String getTableCellValueEC2Stop(WebDriver driver,String colinstanceName) throws InterruptedException
	{
		if (searchCounter==0)
		{
			String search="Stopped";
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchString));
			driver.findElement(By.xpath("//div[@id='gwt-debug-pill-0']//div[@class='GFRVLNABIPB']//div//a[@class='gwt-Anchor GFRVLNABJOB']")).click();
			driver.findElement(searchString).clear();
			driver.findElement(searchString).sendKeys(search);
			Thread.sleep(2500);
			driver.findElement(searchString).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounter++;
		}
		
		String InstanceName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div"));
		int instanceNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceName = driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_instanceName.equals("Name"))
			{
				instanceNamePosition=i;
				break;
			}
		}
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));

		for (; EC2Stop_rowCounter<= rowElement.size(); EC2Stop_rowCounter++)
		{
			InstanceName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2Stop_rowCounter+"]/td)["+instanceNamePosition+"]")).getText();
			
			if (InstanceName.equals(colinstanceName))
			{
				driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2Stop_rowCounter+"]/td)["+instanceNamePosition+"]")).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnActions));
				driver.findElement(clickOnActions).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnInstanceState));
				driver.findElement(clickOnInstanceState).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnStart));
				driver.findElement(clickOnStart).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnConfirmStart));
				driver.findElement(clickOnConfirmStart).click();
				Thread.sleep(2500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnRefresh));
				driver.findElement(clickOnRefresh).click();
				
				break;
			}
		}
		return null;
	}
	
	public void searchForOCPEC2() throws InterruptedException
	{
		if (searchCounter==0)
		{
			String search="OCP-TEST-01";
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchString));
			driver.findElement(searchString).clear();
			driver.findElement(searchString).sendKeys(search);
			Thread.sleep(2500);
			driver.findElement(searchString).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounter++;
		}
	}
	
	public void searchForOCPInstanceName(String name) throws InterruptedException
	{
		if (searchCounter==0)
		{
			String search=name;
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchString));
			driver.findElement(searchString).clear();
			driver.findElement(searchString).sendKeys(search);
			Thread.sleep(2500);
			driver.findElement(searchString).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounter++;
		}
	}
	
	
	
	public void searchForOCPVPC() throws InterruptedException
	{
		if (searchCounterVPC==0)
		{
			String searchVPC="OCP_";
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchStringVPC));
			driver.findElement(searchStringVPC).clear();
			driver.findElement(searchStringVPC).sendKeys(searchVPC);
			Thread.sleep(2500);
			driver.findElement(searchStringVPC).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			searchCounterVPC++;
		}
	}
	
	public void searchForPrivatePublicSubnet() throws InterruptedException
	{
		if (searchCounterSubnet==0)
		{
			String searchSubnet="-SUBNET-";
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchStringSubnet));
			driver.findElement(searchStringSubnet).clear();
			driver.findElement(searchStringSubnet).sendKeys(searchSubnet);
			Thread.sleep(2500);
			driver.findElement(searchStringSubnet).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounterSubnet++;
		}
	}
	
	public void searchForSecurityGroup() throws InterruptedException
	{
		if (searchCounterSecurityGroupIB==0)
		{
			String searchSecurityGroup="OCP-TEST-01";
			Thread.sleep(2500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchStringSecurityGroup));
			driver.findElement(searchStringSecurityGroup).clear();
			driver.findElement(searchStringSecurityGroup).sendKeys(searchSecurityGroup);
			Thread.sleep(2500);
			driver.findElement(searchStringSecurityGroup).sendKeys(Keys.ENTER);
			Thread.sleep(2500);
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounterSecurityGroupIB++;
		}
	}
	
	public String getTableCellValueVPC(WebDriver driver,String vpcName, String vpcState, String vpcCIDR) throws InterruptedException
	{
		searchForOCPVPC();
		String VPCName=null, VPCState=null, VPCCIDR=null, sActualVPC=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/table/thead/tr/th/div/div/div"));
		int vpcNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_vpcName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_vpcName.equals("Name"))
			{
				vpcNamePosition=i;
				break;
			}
		}
		int vpcStatePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_vpcState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_vpcState.equals("State"))
			{
				vpcStatePosition=i;
				break;
			}
		}
		int vpcIPv4CIDRPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_IPv4CIDR= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_IPv4CIDR.equals("IPv4 CIDR"))
			{
				vpcIPv4CIDRPosition=i;
				break;
			}
		}
		
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/table/tbody/tr"));

		//Finds Elements
		for (; VPC_rowCounter<= rowElement.size(); VPC_rowCounter++)
		{
			VPCName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/tbody/tr["+VPC_rowCounter+"]/td)["+vpcNamePosition+"]")).getText();
			VPCState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/tbody/tr["+VPC_rowCounter+"]/td)["+vpcStatePosition+"]")).getText();
			VPCCIDR = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/tbody/tr["+VPC_rowCounter+"]/td)["+vpcIPv4CIDRPosition+"]")).getText();
			sActualVPC = VPCName + VPCState + VPCCIDR;
			//System.out.println("Act String:"+sActualVPC);
			VPC_rowCounter++;
			break;
		}
		return sActualVPC;
	}

	public String getTableCellValueSubnet(WebDriver driver,String subnetName,String subnetState,String subnetAvailabilityZone) throws InterruptedException
	{
		searchForPrivatePublicSubnet();
		String SubnetName=null, SubnetState=null, SubnetAvailabilityZone=null, sActualSubnet=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div"));
		int subnetNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_subnetName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_subnetName.equals("Name"))
			{
				subnetNamePosition=i;
				break;
			}
		}

		int subnetStatePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_subnetState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_subnetState.equals("State"))
			{
				subnetStatePosition=i;
				break;
			}
		}
		
		int subnetAvailibilityPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_subnetAvailibility= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_subnetAvailibility.equals("Availability Zone"))
			{
				subnetAvailibilityPosition=i;
				break;
			}
		}
		
	
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		for (; Subnet_Counter<= rowElement.size(); Subnet_Counter++)
		{
			SubnetName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+Subnet_Counter+"]/td)["+subnetNamePosition+"]")).getText();
			SubnetState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+Subnet_Counter+"]/td)["+subnetStatePosition+"]")).getText();
			SubnetAvailabilityZone = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+Subnet_Counter+"]/td)["+subnetAvailibilityPosition+"]")).getText();
			
			sActualSubnet = SubnetName + SubnetState + SubnetAvailabilityZone;
			//System.out.println("Act String:"+sActualSubnet);
			Subnet_Counter++;
			break;
		}
		
		return sActualSubnet;
	}
	
	public List<String> getTableCellValueSecurityGroupIB(WebDriver driver,String colsgName,String colibType,String colibProtocol,String colibSource) throws InterruptedException
	{
		searchForSecurityGroup();
		String SgName=null, SgType=null, SgProtocol=null, SgSource=null, sActualSGIB=null;
		List<String> list_SGIBActual = new ArrayList<String>();
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div"));
		int sgNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgName.equals("Name"))
			{
				sgNamePosition=i;
				break;
			}
		}
		
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		for (;SecurityGroup_Counter<= rowElement.size();)
		{
			SgName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+SecurityGroup_Counter+"]/td)["+sgNamePosition+"]")).getText();
			
			driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+SecurityGroup_Counter+"]/td)["+sgNamePosition+"]")).click();
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnInbound));
			driver.findElement(clickOnInbound).click();
			
			List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
			int sgIBTypePosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_sgIBType.equals("Type"))
				{
					sgIBTypePosition=i;
					break;
				}
			}
			int sgIBProtocolPosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_sgIBProtocol.equals("Protocol"))
				{
					sgIBProtocolPosition=i+1;
					break;
				}
			}
			int sgIBSourcePosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_sgIBSource.equals("Port Range"))
				{
					sgIBSourcePosition=i+2;
					break;
				}
			}
				
			List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
			for (int SGIB_Counter=1; SGIB_Counter< rowElementSGIB.size();)
			{
					SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
					SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
					SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
					sActualSGIB = SgName + SgType + SgProtocol + SgSource;
					list_SGIBActual.add(sActualSGIB);
					SGIB_Counter++;
			}
			SecurityGroup_Counter++;
			return list_SGIBActual;
		}
		return null;
	}

	public String getTableCellValueSecurityGroup(WebDriver driver2, String sgName) throws InterruptedException 
	{
		searchForSecurityGroup();
		String SgName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div"));
		int sgNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgName.equals("Name"))
			{
				sgNamePosition=i;
				break;
			}
		}
		
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		for (;SecurityGroup_Counter<= rowElement.size();)
		{
			SgName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+SecurityGroup_Counter+"]/td)["+sgNamePosition+"]")).getText();
			SecurityGroup_Counter++;
			break;
		}
		return SgName;
	}
	
	public String getTableCellValueSecurityGroupBastonIB(WebDriver driver,String colibType,String colibProtocol,String colibSource) throws InterruptedException
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGBastonIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGBastonIB_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGBastonIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGBastonIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGBastonIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			sActualSGBastonIB = SgType + SgProtocol + SgSource;
			SGBastonIB_Counter++;
			break;
		}
		return sActualSGBastonIB;
	}

	public String getTableCellValueSecurityGroupGlusterIB(WebDriver driver, String ibType, String ibProtocol, String ibSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGGlusterIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGGlusterIB_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGGlusterIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGGlusterIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGGlusterIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			boolean b = SgSource.contains(ibSource);
			sActualSGGlusterIB = SgType + SgProtocol + b;
			SGGlusterIB_Counter++;
			break;
		}
		return sActualSGGlusterIB;
	}

	public String getTableCellValueSecurityGroupInfraIB(WebDriver driver2, String ibType, String ibProtocol,String ibSource)
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGInfraIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGInfraIB_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGInfraIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGInfraIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGInfraIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			sActualSGInfraIB = SgType + SgProtocol + SgSource;
			SGInfraIB_Counter++;
			break;
		}
		return sActualSGInfraIB;
	
	}

	public String getTableCellValueSecurityGroupInfraSGIB(WebDriver driver2, String ibType, String ibProtocol, String ibSource)
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGInfraSGIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGInfraIBSG_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGInfraIBSG_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGInfraIBSG_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGInfraIBSG_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			sActualSGInfraSGIB = SgType + SgProtocol + SgSource;
			SGInfraIBSG_Counter++;
			break;
		}
		return sActualSGInfraSGIB;
	
	}

	public String getTableCellValueSecurityGroupMasterLBIB(WebDriver driver2, String ibType, String ibProtocol,String ibSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGMasterLBIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGMasterLBIB_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGMasterLBIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGMasterLBIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGMasterLBIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			sActualSGMasterLBIB = SgType + SgProtocol + SgSource;
			SGMasterLBIB_Counter++;
			break;
		}
		return sActualSGMasterLBIB;
	}

	public String getTableCellValueSecurityGroupMasterSGIB(WebDriver driver2, String ibType, String ibProtocol,String ibSource)
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGMasterSGIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGMasterSGIB_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGMasterSGIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGMasterSGIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGMasterSGIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			sActualSGMasterSGIB = SgType + SgProtocol + SgSource;
			SGMasterSGIB_Counter++;
			break;
		}
		return sActualSGMasterSGIB;
	}

	public String getTableCellValueSecurityGroupComputeSGIB(WebDriver driver2, String ibType, String ibProtocol,String ibSource)
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGComputeIB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
				
		int sgIBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgIBTypePosition=i;
				break;
			}
		}
		int sgIBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgIBProtocolPosition=i+1;
				break;
			}
		}
		int sgIBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgIBSourcePosition=i+2;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr"));
		for (;SGComputeIB_Counter< rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGComputeIB_Counter+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGComputeIB_Counter+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/tbody/tr["+SGComputeIB_Counter+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
			sActualSGComputeIB = SgType + SgProtocol + SgSource;
			SGComputeIB_Counter++;
			break;
		}
		return sActualSGComputeIB;
	}

	public String getTableCellValueSecurityGroupBastonOB(WebDriver driver2, String obType, String obProtocol, String obSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGBaston=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGBastonOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGBastonOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGBastonOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGBastonOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGBaston = SgType + SgProtocol + SgSource;
			SGBastonOB_Counter++;
			break;
		}
		return sActualSGBaston;
	}

	public String getTableCellValueSecurityGroupGlusterOB(WebDriver driver2, String obType, String obProtocol, String obSource)
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGGluster=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGGlusterOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGGlusterOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGGlusterOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGGlusterOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGGluster = SgType + SgProtocol + SgSource;
			SGGlusterOB_Counter++;
			break;
		}
		return sActualSGGluster;
	}

	public String getTableCellValueSecurityGroupInfraLBOB(WebDriver driver2, String obType, String obProtocol, String obSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGInfraLB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGInfraLBOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGInfraLBOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGInfraLBOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGInfraLBOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGInfraLB = SgType + SgProtocol + SgSource;
			SGInfraLBOB_Counter++;
			break;
		}
		return sActualSGInfraLB;
	}

	public String getTableCellValueSecurityGroupInfraSGOB(WebDriver driver2, String obType, String obProtocol, String obSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGInfraSG=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGInfraSGOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGInfraSGOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGInfraSGOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGInfraSGOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGInfraSG = SgType + SgProtocol + SgSource;
			SGInfraSGOB_Counter++;
			break;
		}
		return sActualSGInfraSG;
	
	}

	public String getTableCellValueSecurityGroupMasterLBOB(WebDriver driver2, String obType, String obProtocol, String obSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGMasterLB=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGMasterLBOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGMasterLBOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGMasterLBOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGMasterLBOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGMasterLB = SgType + SgProtocol + SgSource;
			SGMasterLBOB_Counter++;
			break;
		}
		return sActualSGMasterLB;	
	}

	public String getTableCellValueSecurityGroupMasterSGOB(WebDriver driver2, String obType, String obProtocol, String obSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGMasterSG=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGMasterSGOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGMasterSGOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGMasterSGOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGMasterSGOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGMasterSG = SgType + SgProtocol + SgSource;
			SGMasterSGOB_Counter++;
			break;
		}
		return sActualSGMasterSG;		
	}

	public String getTableCellValueSecurityGroupComputeOB(WebDriver driver2, String obType, String obProtocol, String obSource) 
	{
		String SgType=null, SgProtocol=null, SgSource=null, sActualSGCompute=null;
		List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th"));
				
		int sgOBTypePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBType.equals("Type"))
			{
				sgOBTypePosition=i;
				break;
			}
		}
		int sgOBProtocolPosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBProtocol.equals("Protocol"))
			{
				sgOBProtocolPosition=i;
				break;
			}
		}
		int sgOBSourcePosition=0;
		for (int i=1;i<colNamesIB.size();i++)
		{
			String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/thead/tr/th)["+i+"]")).getText();
			if (colName_sgIBSource.equals("Port Range"))
			{
				sgOBSourcePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
		for (;SGComputeOB_Counter<= rowElementSGIB.size();)
		{
			SgType = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGComputeOB_Counter+"]/td/div/div)["+sgOBTypePosition+"]")).getText();
			SgProtocol = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGComputeOB_Counter+"]/td/div/div)["+sgOBProtocolPosition+"]")).getText();
			SgSource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDACI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGComputeOB_Counter+"]/td/div/div)["+sgOBSourcePosition+"]")).getText();
			sActualSGCompute = SgType + SgProtocol + SgSource;
			SGComputeOB_Counter++;
			break;
		}
		return sActualSGCompute;
	}

	public String getTableCellValueRouteTable(WebDriver driver2, String rtName, String rtExplicitlyAssociatedWith) 
	{
		String RTName=null, RTExplicitlyAssociatedWith=null, sActualRT=null;
		List<WebElement> colNamesRT = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/table/thead/tr/th"));
				
		int rtNamePosition=0;
		for (int i=1;i<colNamesRT.size();i++)
		{
			String colName_rtName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/thead/tr/th)["+i+"]")).getText();
			if (colName_rtName.equals("Name"))
			{
				rtNamePosition=i;
				break;
			}
		}
		int rtExplicitlyAssociatedWithPosition=0;
		for (int i=1;i<colNamesRT.size();i++)
		{
			String colName_rtExplicitlyAssociatedWith = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/thead/tr/th)["+i+"]")).getText();
			if (colName_rtExplicitlyAssociatedWith.equals("Explicitly Associated With"))
			{
				rtExplicitlyAssociatedWithPosition=i;
				break;
			}
		}
				
		List<WebElement> rowElementRT = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/table/tbody[@style='']/tr"));
		for (;RT_Counter<= rowElementRT.size();)
		{
			RTName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/tbody[@style='']/tr["+RT_Counter+"]/td)["+rtNamePosition+"]")).getText();
			RTExplicitlyAssociatedWith = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/table/tbody[@style='']/tr["+RT_Counter+"]/td)["+rtExplicitlyAssociatedWithPosition+"]")).getText();
			sActualRT = RTName + RTExplicitlyAssociatedWith ;
			RT_Counter++;
			break;
		}
		return sActualRT;

	}

	public String getTableCellValueRouteTablesRoutePublic(WebDriver driver2, String rtDestination, String rtStatus) 
	{
		String RtDestination=null, RtStatus=null, sActualRTRoutePublic=null;
		List<WebElement> colRoutesPublic = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent1']/tbody/tr["+RTRouteUpperTab+"]/td"));
				
		int rtRoutesPublicDestinationPosition=0;
		for (int i=1;i<colRoutesPublic.size();i++)
		{
			String colName_rtRoutesPublicDestinationPosition = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr["+RTRouteUpperTab+"]/td)["+i+"]")).getText();
			if (colName_rtRoutesPublicDestinationPosition.equals("Destination"))
			{
				rtRoutesPublicDestinationPosition=i;
				break;
			}
		}
		int rtRoutesPublicStatus=0;
		for (int i=1;i<colRoutesPublic.size();i++)
		{
			String colName_rtRoutesPublicStatus = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr["+RTRouteUpperTab+"]/td)["+i+"]")).getText();
			if (colName_rtRoutesPublicStatus.equals("Status"))
			{
				rtRoutesPublicStatus=i;
				break;
			}
		}
				
		//(//table[@id='gwt-debug-tabContent1']/tbody/tr[4]/td)[2]
		
		//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE'][1]/td[1]
		
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']"));
		for (;RTRoutesPublic<= rowElementSGIB.size();)
		{
			RtDestination = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTRoutesPublic+"]/td)["+rtRoutesPublicDestinationPosition+"]")).getText();
			RtStatus = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTRoutesPublic+"]/td)["+rtRoutesPublicStatus+"]")).getText();
			sActualRTRoutePublic = RtDestination + RtStatus;
			RTRoutesPublic++;
			break;
		}
		return sActualRTRoutePublic;
	}

	public String getTableCellValueRouteTablesRoutePrivate(WebDriver driver2, String rtDestination, String rtStatus) 
	{
		String RtDestination=null, RtStatus=null, sActualRTRoutePrivate=null;
		List<WebElement> colRoutesPublic = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent1']/tbody/tr["+RTRouteUpperTab+"]/td"));
				
		int rtRoutesPublicDestinationPosition=0;
		for (int i=1;i<colRoutesPublic.size();i++)
		{
			String colName_rtRoutesPublicDestinationPosition = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr["+RTRouteUpperTab+"]/td)["+i+"]")).getText();
			if (colName_rtRoutesPublicDestinationPosition.equals("Destination"))
			{
				rtRoutesPublicDestinationPosition=i;
				break;
			}
		}
		int rtRoutesPublicStatus=0;
		for (int i=1;i<colRoutesPublic.size();i++)
		{
			String colName_rtRoutesPublicStatus = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr["+RTRouteUpperTab+"]/td)["+i+"]")).getText();
			if (colName_rtRoutesPublicStatus.equals("Status"))
			{
				rtRoutesPublicStatus=i;
				break;
			}
		}
				
		//(//table[@id='gwt-debug-tabContent1']/tbody/tr[4]/td)[2]
		
		//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE'][1]/td[1]
		
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']"));
		for (;RTRoutesPrivate<= rowElementSGIB.size();)
		{
			RtDestination = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTRoutesPrivate+"]/td)["+rtRoutesPublicDestinationPosition+"]")).getText();
			RtStatus = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent1']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTRoutesPrivate+"]/td)["+rtRoutesPublicStatus+"]")).getText();
			sActualRTRoutePrivate = RtDestination + RtStatus;
			RTRoutesPrivate++;
			break;
		}
		return sActualRTRoutePrivate;
	}

	public String getTableCellValueRouteTablesSubnetAssociationsPublic(WebDriver driver2, String rtSubnet,String rtIPV4)
	{
		String RtSubnet=null, RtIPV4=null, sActualRTSubnetAssociationsPublic=null;
		List<WebElement> colSubnetAssociationsPublic = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent2']/tbody/tr["+RTSubnetAssociationsTab+"]/td"));
				
		int rtSubnetAssociationsPublicSubnetPosition=0;
		for (int i=1;i<colSubnetAssociationsPublic.size();i++)
		{
			String colName_rtSubnetAssociationsPublicPosition = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr["+RTSubnetAssociationsTab+"]/td)["+i+"]")).getText();
			if (colName_rtSubnetAssociationsPublicPosition.equals("Subnet"))
			{
				rtSubnetAssociationsPublicSubnetPosition=i;
				break;
			}
		}
		int rtSubnetAssociationsPublicIPV4Position=0;
		for (int i=1;i<colSubnetAssociationsPublic.size();i++)
		{
			String colName_rtRoutesPublicStatus = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr["+RTSubnetAssociationsTab+"]/td)["+i+"]")).getText();
			if (colName_rtRoutesPublicStatus.equals("IPv4 CIDR"))
			{
				rtSubnetAssociationsPublicIPV4Position=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent2']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']"));
		for (;RTSubnetAssociationsPublic<= rowElementSGIB.size();)
		{
			RtSubnet = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTSubnetAssociationsPublic+"]/td)["+rtSubnetAssociationsPublicSubnetPosition+"]")).getText();
			boolean b = RtSubnet.contains(rtSubnet);
			RtIPV4 = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTSubnetAssociationsPublic+"]/td)["+rtSubnetAssociationsPublicIPV4Position+"]")).getText();
			sActualRTSubnetAssociationsPublic = b + RtIPV4;
			RTSubnetAssociationsPublic++;
			break;
		}
		return sActualRTSubnetAssociationsPublic;
	}

	public String getTableCellValueRouteTablesSubnetAssociationsPrivate(WebDriver driver2, String rtSubnet, String rtIPV4) 
	{
		String RtSubnet=null, RtIPV4=null, sActualRTSubnetAssociationsPrivate=null;
		List<WebElement> colSubnetAssociationsPrivate = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent2']/tbody/tr["+RTSubnetAssociationsTab+"]/td"));
				
		int rtSubnetAssociationsPrivateSubnetPosition=0;
		for (int i=1;i<colSubnetAssociationsPrivate.size();i++)
		{
			String colName_rtSubnetAssociationsPrivatePosition = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr["+RTSubnetAssociationsTab+"]/td)["+i+"]")).getText();
			if (colName_rtSubnetAssociationsPrivatePosition.equals("Subnet"))
			{
				rtSubnetAssociationsPrivateSubnetPosition=i;
				break;
			}
		}
		int rtSubnetAssociationsPrivateIPV4Position=0;
		for (int i=1;i<colSubnetAssociationsPrivate.size();i++)
		{
			String colName_rtRoutesPrivateStatus = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr["+RTSubnetAssociationsTab+"]/td)["+i+"]")).getText();
			if (colName_rtRoutesPrivateStatus.equals("IPv4 CIDR"))
			{
				rtSubnetAssociationsPrivateIPV4Position=i;
				break;
			}
		}
				
		List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//table[@id='gwt-debug-tabContent2']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']"));
		for (;RTSubnetAssociationsPrivate<= rowElementSGIB.size();)
		{
			RtSubnet = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTSubnetAssociationsPrivate+"]/td)["+rtSubnetAssociationsPrivateSubnetPosition+"]")).getText();
			boolean b = RtSubnet.contains(rtSubnet);
			RtIPV4 = driver.findElement(By.xpath("(//table[@id='gwt-debug-tabContent2']/tbody/tr[@class='GH4A0MBJE' or @class='GH4A0MBJE' or @class='GH4A0MBJE']["+RTSubnetAssociationsPrivate+"]/td)["+rtSubnetAssociationsPrivateIPV4Position+"]")).getText();
			sActualRTSubnetAssociationsPrivate = b + RtIPV4;
			RTSubnetAssociationsPrivate++;
			break;
		}
		return sActualRTSubnetAssociationsPrivate;
	}
	
	public void ec2InstaceSortASC() throws InterruptedException
	{
		if (searchCounterSecurity==0)
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(sortByNameASC));
			driver.findElement(sortByNameASC).click();
			searchCounterSecurity++;
		}
	}

	public void getTableCellValueEC2(WebDriver driver2) throws InterruptedException, IOException, AWTException 
	{
		String InstanceName=null, InstanceType=null, InstanceState=null, InstanceKeyName=null, InstanceSecurityGroup=null, sActual=null;
		ec2InstaceSortASC();
		List <String> lst = new LinkedList<String>();
		Thread.sleep(2000);
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div"));
		int instanceNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceName = driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_instanceName.equals("Name"))
			{
				instanceNamePosition=i;
				break;
			}
		}
		int instanceTypePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceType = driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceType.equals("Instance Type"))
			{
				instanceTypePosition=i;
				break;
			}
		}
		int instanceStatePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceState= driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceState.equals("Instance State"))
			{
				instanceStatePosition=i;
				break;
			}
		}
		int instanceKeyNamePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_instanceKeyName= driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceKeyName.equals("Key Name"))
			{
				instanceKeyNamePosition=i;
				break;
			}
		}
		int instanceSecurityGroupPosition = 0;
		for (int i=2;i<=colNames.size();i++)
		{
			String colName_instanceSecurityGroup= driver.findElement(By.xpath("(//div[@class='gwt-SplitLayoutPanel']/div/div/div/div/div/table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_instanceSecurityGroup.equals("Security Groups"))
			{
				instanceSecurityGroupPosition=i;
				break;
			}
		}
		Thread.sleep(2000);
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		for (; EC2_rowCounterSecurity<= rowElement.size();)
		{
			if (EC2_rowCounterSecurity==10)
			{
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
				
			}
			InstanceName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounterSecurity+"]/td)["+instanceNamePosition+"]")).getText();
			InstanceType = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounterSecurity+"]/td)["+instanceTypePosition+"]")).getText();
			InstanceState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounterSecurity+"]/td)["+instanceStatePosition+"]")).getText();
			InstanceKeyName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounterSecurity+"]/td)["+instanceKeyNamePosition+"]")).getText();
			InstanceSecurityGroup = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+EC2_rowCounterSecurity+"]/td)["+instanceSecurityGroupPosition+"]//*")).getText();
			sActual = InstanceName + InstanceType + InstanceState + InstanceKeyName + InstanceSecurityGroup;
			//if (EC2_rowCounterSecurity==18)
			//{
				//System.out.println(sActual);
			//}
			String[] dataToWrite={InstanceName,InstanceType,InstanceState,InstanceKeyName, InstanceSecurityGroup}; 
			WriteExcel w = new WriteExcel();
			w.writeExcel(excelFileWrite, "ExportExcel.xlsx", "EC2Verification", dataToWrite);
			EC2_rowCounterSecurity++;
		}
	}

	public void sgSort() throws InterruptedException
	{
		if (searchCounterSG==0)
		{
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(sortByNameASC));
			driver.findElement(sortByNameASC).click();
			Thread.sleep(2500);
			searchCounterSG++;
		}
	}
	
	public void getTableCellValueSecurityGroup(WebDriver driver2) throws IOException, InterruptedException 
	{
		String SgName=null, SgGroupID=null, SgGroupName=null, SgVPCID=null, SgDescription=null, sActualSG=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div"));
		sgSort();
		int sgNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgName.equals("Name"))
			{
				sgNamePosition=i;
				break;
			}
		}
		int sgGroupIDPosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgGroupID = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgGroupID.equals("Group ID"))
			{
				sgGroupIDPosition=i;
				break;
			}
		}
		int sgGroupNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgGroupName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgGroupName.equals("Group Name"))
			{
				sgGroupNamePosition=i;
				break;
			}
		}
		
		int sgDescriptionPosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgDescription = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgDescription.equals("Description"))
			{
				sgDescriptionPosition=i;
				break;
			}
		}
		
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@class='GNAEPMSDENG']/table/tbody/tr"));
		
		for (; SG_rowCounterSecurity<= rowElement.size();)
		{
			//Thread.sleep(1500);
			SgName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurity+"]/td)["+sgNamePosition+"]")).getText();
			SgGroupID = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurity+"]/td)["+sgGroupIDPosition+"]")).getText();
			SgGroupName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurity+"]/td)["+sgGroupNamePosition+"]")).getText();
			SgDescription = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurity+"]/td["+sgDescriptionPosition+"])")).getText();
			sActualSG = SgName + SgGroupID + SgGroupName + SgDescription;
			//System.out.println(sActualSG);
			String[] dataToWrite={SgName, SgGroupID, SgGroupName, SgDescription}; 
			WriteExcel w = new WriteExcel();
			w.writeExcel(excelFileWrite, "ExportExcel.xlsx", "SecurityGroupVerification", dataToWrite);
			SG_rowCounterSecurity++;
		}
		
		//51-100
		boolean nextPage = driver.findElement(By.xpath("//div[@id='gwt-debug-paginatorNext']")).isEnabled();
		if (nextPage==true)
		{
			driver.findElement(By.xpath("//div[@id='gwt-debug-paginatorNext']")).click();
			List<WebElement> rowElementNextPage = driver.findElements(By.xpath("//div[@class='GNAEPMSDENG']/table/tbody/tr"));
			for (; SG_rowCounterSecurityNextPage<= rowElementNextPage.size();)
			{
				//Thread.sleep(1500);
				SgName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage+"]/td)["+sgNamePosition+"]")).getText();
				SgGroupID = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage+"]/td)["+sgGroupIDPosition+"]")).getText();
				SgGroupName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage+"]/td)["+sgGroupNamePosition+"]")).getText();
				SgDescription = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage+"]/td["+sgDescriptionPosition+"])")).getText();
				sActualSG = SgName + SgGroupID + SgGroupName + SgDescription;
				//System.out.println(sActualSG);
				String[] dataToWrite={SgName, SgGroupID, SgGroupName, SgDescription}; 
				WriteExcel w = new WriteExcel();
				w.writeExcel(excelFileWrite, "ExportExcel.xlsx", "SecurityGroupVerification", dataToWrite);
				SG_rowCounterSecurityNextPage++;
			}
		}
		
		//101-150
		boolean nextPage1 = driver.findElement(By.xpath("//div[@id='gwt-debug-paginatorNext']")).isEnabled();
		if (nextPage1==true)
		{
			driver.findElement(By.xpath("//div[@id='gwt-debug-paginatorNext']")).click();
			List<WebElement> rowElementNextPage1 = driver.findElements(By.xpath("//div[@class='GNAEPMSDENG']/table/tbody/tr"));
			for (; SG_rowCounterSecurityNextPage1<= rowElementNextPage1.size();)
			{
				//Thread.sleep(1500);
				SgName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage1+"]/td)["+sgNamePosition+"]")).getText();
				SgGroupID = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage1+"]/td)["+sgGroupIDPosition+"]")).getText();
				SgGroupName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage1+"]/td)["+sgGroupNamePosition+"]")).getText();
				SgDescription = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage1+"]/td["+sgDescriptionPosition+"])")).getText();
				sActualSG = SgName + SgGroupID + SgGroupName + SgDescription;
				//System.out.println(sActualSG);
				String[] dataToWrite={SgName, SgGroupID, SgGroupName, SgDescription}; 
				WriteExcel w = new WriteExcel();
				w.writeExcel(excelFileWrite, "ExportExcel.xlsx", "SecurityGroupVerification", dataToWrite);
				SG_rowCounterSecurityNextPage1++;
			}
		}
		
		//151-200
		boolean nextPage2 = driver.findElement(By.xpath("//div[@id='gwt-debug-paginatorNext']")).isEnabled();
		if (nextPage2==true)
		{
			driver.findElement(By.xpath("//div[@id='gwt-debug-paginatorNext']")).click();
			List<WebElement> rowElementNextPage1 = driver.findElements(By.xpath("//div[@class='GNAEPMSDENG']/table/tbody/tr"));
			for (; SG_rowCounterSecurityNextPage2<= rowElementNextPage1.size();)
			{
				//Thread.sleep(1500);
				SgName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage2+"]/td)["+sgNamePosition+"]")).getText();
				SgGroupID = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage2+"]/td)["+sgGroupIDPosition+"]")).getText();
				SgGroupName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage2+"]/td)["+sgGroupNamePosition+"]")).getText();
				SgDescription = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+SG_rowCounterSecurityNextPage2+"]/td["+sgDescriptionPosition+"])")).getText();
				sActualSG = SgName + SgGroupID + SgGroupName + SgDescription;
				//System.out.println(sActualSG);
				String[] dataToWrite={SgName, SgGroupID, SgGroupName, SgDescription}; 
				WriteExcel w = new WriteExcel();
				w.writeExcel(excelFileWrite, "ExportExcel.xlsx", "SecurityGroupVerification", dataToWrite);
				SG_rowCounterSecurityNextPage2++;
			}
		}
	}

	public void getTableCellValueSecurityGroupInbound(WebDriver driver2) throws InterruptedException, IOException 
	{
		String SgName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div"));
		sgSort();
		int sgNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_sgName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']/div/div/table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_sgName.equals("Name"))
			{
				sgNamePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@class='GNAEPMSDDNG']/table/tbody/tr"));
		
		for (; SGInbound_rowCounterSecurity<= rowElement.size();)
		{
			SgName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDDNG']/table/tbody/tr["+SGInbound_rowCounterSecurity+"]/td)["+sgNamePosition+"]")).getText();
			System.out.println(SgName);
			
			driver.findElement(By.xpath("(//div[@class='GNAEPMSDDNG']/table/tbody/tr["+SGInbound_rowCounterSecurity+"]/td)["+sgNamePosition+"]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnInbound));
			driver.findElement(clickOnInbound).click();
			
			String SgType=null, SgProtocol=null, SgSource=null;
			List<WebElement> colNamesIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th"));
					
			int sgIBTypePosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_sgIBType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_sgIBType.equals("Type"))
				{
					sgIBTypePosition=i;
					break;
				}
			}
			int sgIBProtocolPosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_sgIBProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_sgIBProtocol.equals("Protocol"))
				{
					sgIBProtocolPosition=i+1;
					break;
				}
			}
			int sgIBSourcePosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_sgIBSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_sgIBSource.equals("Port Range"))
				{
					sgIBSourcePosition=i+2;
					break;
				}
			}
					
			List<WebElement> rowElementSGIB = driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']/div/div[@class='GNAEPMSDNBI gwt-TabLayoutPanelContent']/div/table/tbody/tr"));
			
			for (int SGIB_CounterSecurity=1;SGIB_CounterSecurity<=rowElementSGIB.size();)
			{
				SgType = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div[@class='GNAEPMSDNBI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGIB_CounterSecurity+"]/td/div/div/div)["+sgIBTypePosition+"]")).getText();
				SgProtocol = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div[@class='GNAEPMSDNBI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGIB_CounterSecurity+"]/td/div/div/div)["+sgIBProtocolPosition+"]")).getText();
				SgSource = driver.findElement(By.xpath("(//div[@class='gwt-TabLayoutPanelContentContainer']/div/div[@class='GNAEPMSDNBI gwt-TabLayoutPanelContent']/div/table/tbody/tr["+SGIB_CounterSecurity+"]/td/div/div/div)["+sgIBSourcePosition+"]")).getText();
				String[] dataToWrite1={SgName,SgType,SgProtocol,SgSource}; 
				WriteExcel w1 = new WriteExcel();
				w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "SGInbound", dataToWrite1);
				SGIB_CounterSecurity++;
			}
			SGInbound_rowCounterSecurity++;
		}
	}

	public void getTableCellValueVPCSecurity(WebDriver driver2) throws IOException 
	{
		String VPCName=null, VPCState=null, VPCCIDR=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div"));
		int vpcNamePosition=0;
		
		for (int i=2;i<colNames.size();i++)
		{
			String colName_vpcName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_vpcName.equals("Name"))
			{
				vpcNamePosition=i;
				break;
			}
		}
		int vpcStatePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_vpcState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_vpcState.equals("State"))
			{
				vpcStatePosition=i;
				break;
			}
		}
		int vpcIPv4CIDRPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_IPv4CIDR= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_IPv4CIDR.equals("IPv4 CIDR"))
			{
				vpcIPv4CIDRPosition=i;
				break;
			}
		}
		
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody[@style='']/tr"));

		for (; VPC_rowCounterSecurity<= rowElement.size();)
		{
			VPCName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody[@style='']/tr["+VPC_rowCounterSecurity+"]/td)["+vpcNamePosition+"]")).getText();
			VPCState = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody[@style='']/tr["+VPC_rowCounterSecurity+"]/td)["+vpcStatePosition+"]")).getText();
			VPCCIDR = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody[@style='']/tr["+VPC_rowCounterSecurity+"]/td)["+vpcIPv4CIDRPosition+"]")).getText();
			String[] dataToWrite1={VPCName,VPCState,VPCCIDR}; 
			WriteExcel w1 = new WriteExcel();
			w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "VPC", dataToWrite1);
			VPC_rowCounterSecurity++;
		}
	}

	public void getTableCellValueAMISecurity(WebDriver driver2) throws IOException, InterruptedException 
	{
		String AMIName=null, AMIAMIName=null, AMISource=null, AMIOwner=null, AMIVisibility=null, AMIStatus=null;
		Thread.sleep(3000);
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div"));
		int amiNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_amiName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_amiName.equals("Name"))
			{
				amiNamePosition=i;
				break;
			}
		}
		int amiAMIPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_amiAMIName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_amiAMIName.equals("AMI Name"))
			{
				amiAMIPosition=i;
				break;
			}
		}
		int amiSourcePosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_amiSource= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_amiSource.equals("Source"))
			{
				amiSourcePosition=i;
				break;
			}
		}
		int amiOwnerPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_amiOwner= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_amiOwner.equals("Owner"))
			{
				amiOwnerPosition=i;
				break;
			}
		}
		int amiVisibilityPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_amiVisibility= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_amiVisibility.equals("Visibility"))
			{
				amiVisibilityPosition=i;
				break;
			}
		}
		int amiStatusPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_amiStatus= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_amiStatus.equals("Status"))
			{
				amiStatusPosition=i;
				break;
			}
		}
		Thread.sleep(3000);
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@class='GNAEPMSDENG']/table/tbody/tr"));
		for (; AMI_rowCounterSecurity<= rowElement.size();)
		{
			AMIName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+AMI_rowCounterSecurity+"]/td)["+amiNamePosition+"]")).getText();
			AMIAMIName = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+AMI_rowCounterSecurity+"]/td)["+amiAMIPosition+"]")).getText();
			AMISource = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+AMI_rowCounterSecurity+"]/td)["+amiSourcePosition+"]")).getText();
			AMIOwner = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+AMI_rowCounterSecurity+"]/td)["+amiOwnerPosition+"]")).getText();
			AMIVisibility = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+AMI_rowCounterSecurity+"]/td)["+amiVisibilityPosition+"]")).getText();
			AMIStatus = driver.findElement(By.xpath("(//div[@class='GNAEPMSDENG']/table/tbody/tr["+AMI_rowCounterSecurity+"]/td)["+amiStatusPosition+"]")).getText();
			
			String[] dataToWrite1={AMIName,AMIAMIName,AMISource,AMIOwner,AMIVisibility,AMIStatus}; 
			WriteExcel w1 = new WriteExcel();
			w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "AMI", dataToWrite1);
			AMI_rowCounterSecurity++;
		}
	}

	public void getTableCellValueNACLSecurity(WebDriver driver2) throws InterruptedException, IOException 
	{
		String NACLName=null, NACLID=null, NACLAssociatedWith=null, NACLDefault=null, NACLVPC=null;
		Thread.sleep(3000);
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div"));
		int naclNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_naclName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_naclName.equals("Name"))
			{
				naclNamePosition=i;
				break;
			}
		}
		int naclIDIPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_naclID = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_naclID.equals("Network ACL ID"))
			{
				naclIDIPosition=i;
				break;
			}
		}
		int associationPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_association= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_association.equals("Associated With"))
			{
				associationPosition=i;
				break;
			}
		}
		int defaultPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_default= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_default.equals("Default"))
			{
				defaultPosition=i;
				break;
			}
		}
		int naclVPCPosition = 0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_naclVPC= driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+ i +"]")).getText();
			if (colName_naclVPC.equals("VPC"))
			{
				naclVPCPosition=i;
				break;
			}
		}
		
		Thread.sleep(3000);
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@class='GH4A0MBCLF']/table/tbody/tr[@class]"));
		for (; NACL_rowCounterSecurity<= rowElement.size();)
		{
			NACLName = driver.findElement(By.xpath("(//div[@class='GH4A0MBCLF']/table/tbody/tr[@class]["+NACL_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).getText();
			NACLID = driver.findElement(By.xpath("(//div[@class='GH4A0MBCLF']/table/tbody/tr[@class]["+NACL_rowCounterSecurity+"]/td)["+naclIDIPosition+"]")).getText();
			NACLAssociatedWith = driver.findElement(By.xpath("(//div[@class='GH4A0MBCLF']/table/tbody/tr[@class]["+NACL_rowCounterSecurity+"]/td)["+associationPosition+"]")).getText();
			NACLDefault = driver.findElement(By.xpath("(//div[@class='GH4A0MBCLF']/table/tbody/tr[@class]["+NACL_rowCounterSecurity+"]/td)["+defaultPosition+"]")).getText();
			NACLVPC = driver.findElement(By.xpath("(//div[@class='GH4A0MBCLF']/table/tbody/tr[@class]["+NACL_rowCounterSecurity+"]/td)["+naclVPCPosition+"]")).getText();
			
			String[] dataToWrite1={NACLName,NACLID,NACLAssociatedWith,NACLDefault,NACLVPC}; 
			WriteExcel w1 = new WriteExcel();
			w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "NACL", dataToWrite1);
			NACL_rowCounterSecurity++;
		}

	}

	public void getTableCellValueNACLInboundSecurity(WebDriver driver2) throws InterruptedException, IOException 
	{
		String NACLName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div"));
		sgSort();
		int naclNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_naclName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_naclName.equals("Name"))
			{
				naclNamePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		
		for (; NACLInbound_rowCounterSecurity<= rowElement.size();)
		{
			NACLName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+NACLInbound_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).getText();
			//System.out.println(NACLName);
			
			driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+NACLInbound_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnInboundRules));
			driver.findElement(clickOnInboundRules).click();
			
			String NACLRole=null, NACLType=null, NACLProtocol=null, NACLSource=null, NACLAllowDeny=null;
			List<WebElement> colNamesIB = driver.findElements(By.xpath("//table[@id='gwt-debug-inboundRules']/thead/tr/th"));
					
			int naclIBRolePosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_naclIBRole = driver.findElement(By.xpath("(//table[@id='gwt-debug-inboundRules']/thead/tr/th)["+i+"]")).getText();
				if (colName_naclIBRole.equals("Rule #"))
				{
					naclIBRolePosition=i;
					break;
				}
			}
			int naclIBTypePosition=0;
			for (int i=1;i<colNamesIB.size();i++)
			{
				String colName_naclIBTypePosition = driver.findElement(By.xpath("(//table[@id='gwt-debug-inboundRules']/thead/tr/th)["+i+"]")).getText();
				if (colName_naclIBTypePosition.equals("Type"))
				{
					naclIBTypePosition=i;
					break;
				}
			}
			//*******************					
			List<WebElement> rowElementNACLIB = driver.findElements(By.xpath("//table[@id='gwt-debug-inboundRules']/tbody/tr"));
			
			for (int NACLIB_CounterSecurity=1;NACLIB_CounterSecurity<=rowElementNACLIB.size();)
			{
				NACLRole = driver.findElement(By.xpath("(//table[@id='gwt-debug-inboundRules']/tbody/tr["+NACLIB_CounterSecurity+"]/td)["+naclIBRolePosition+"]")).getText();
				NACLType = driver.findElement(By.xpath("(//table[@id='gwt-debug-inboundRules']/tbody/tr["+NACLIB_CounterSecurity+"]/td)["+naclIBTypePosition+"]")).getText();
				String[] dataToWrite1={NACLName,NACLRole,NACLType}; 
				WriteExcel w1 = new WriteExcel();
				w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "NACLInbound", dataToWrite1);
				NACLIB_CounterSecurity++;
			}
			NACLInbound_rowCounterSecurity++;
		}
	}

	public void getTableCellValueNACLOutboundSecurity(WebDriver driver2) throws InterruptedException, IOException 
	{
		String NACLName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div"));
		sgSort();
		int naclNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_naclName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_naclName.equals("Name"))
			{
				naclNamePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		
		for (; NACLOutbound_rowCounterSecurity<= rowElement.size();)
		{
			NACLName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+NACLOutbound_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).getText();
			//System.out.println(NACLName);
			
			driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+NACLOutbound_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnOutboundRules));
			driver.findElement(clickOnOutboundRules).click();
			
			//*****************************************
			
			String NACLRole=null, NACLType=null, NACLProtocol=null, NACLSource=null, NACLAllowDeny=null;
			List<WebElement> colNamesOB = driver.findElements(By.xpath("//table[@id='gwt-debug-outboundRules']/thead/tr/th"));
					
			int naclOBRolePosition=0;
			for (int i=1;i<colNamesOB.size();i++)
			{
				String colName_naclOBRole = driver.findElement(By.xpath("(//table[@id='gwt-debug-outboundRules']/thead/tr/th)["+i+"]")).getText();
				if (colName_naclOBRole.equals("Rule #"))
				{
					naclOBRolePosition=i;
					break;
				}
			}
			int naclOBTypePosition=0;
			for (int i=1;i<colNamesOB.size();i++)
			{
				String colName_naclOBTypePosition = driver.findElement(By.xpath("(//table[@id='gwt-debug-outboundRules']/thead/tr/th)["+i+"]")).getText();
				if (colName_naclOBTypePosition.equals("Type"))
				{
					naclOBTypePosition=i;
					break;
				}
			}
			//*******************					
			List<WebElement> rowElementNACLOB = driver.findElements(By.xpath("//table[@id='gwt-debug-outboundRules']/tbody/tr"));
			
			for (int NACLOB_CounterSecurity=1;NACLOB_CounterSecurity<=rowElementNACLOB.size();)
			{
				NACLRole = driver.findElement(By.xpath("(//table[@id='gwt-debug-outboundRules']/tbody/tr["+NACLOB_CounterSecurity+"]/td)["+naclOBRolePosition+"]")).getText();
				NACLType = driver.findElement(By.xpath("(//table[@id='gwt-debug-outboundRules']/tbody/tr["+NACLOB_CounterSecurity+"]/td)["+naclOBTypePosition+"]")).getText();
				String[] dataToWrite1={NACLName,NACLRole,NACLType}; 
				WriteExcel w1 = new WriteExcel();
				w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "NACLOutbound", dataToWrite1);
				NACLOB_CounterSecurity++;
			}
			NACLOutbound_rowCounterSecurity++;
		}

	}

	public void getTableCellValueNACLSubnetAssociationsSecurity(WebDriver driver2) throws InterruptedException, IOException 
	{
		String NACLName=null;
		List<WebElement> colNames = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div"));
		sgSort();
		int naclNamePosition=0;
		for (int i=2;i<colNames.size();i++)
		{
			String colName_naclName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/thead/tr/th/div/div/div)["+i+"]")).getText();
			if (colName_naclName.equals("Name"))
			{
				naclNamePosition=i;
				break;
			}
		}
				
		List<WebElement> rowElement = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable']//table/tbody/tr"));
		
		for (; NACLSubnetAssociations_rowCounterSecurity<= rowElement.size();)
		{
			NACLName = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+NACLSubnetAssociations_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).getText();
			//System.out.println(NACLName);
			
			driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable']//table/tbody/tr["+NACLSubnetAssociations_rowCounterSecurity+"]/td)["+naclNamePosition+"]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnSubnetAssociations));
			driver.findElement(clickOnSubnetAssociations).click();
			
			String NACLSubnet=null, NACLIPV4=null, NACLProtocol=null, NACLSource=null, NACLAllowDeny=null;
			List<WebElement> colNamesSA = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable-subnetAssociations-subnetsGrid']/div/div/table/thead/tr/th"));
					
			int naclSASubnetPosition=0;
			for (int i=1;i<colNamesSA.size();i++)
			{
				String colName_naclSASubnet = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable-subnetAssociations-subnetsGrid']/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_naclSASubnet.equals("Subnet ID"))
				{
					naclSASubnetPosition=i;
					break;
				}
			}
			int naclSAIPV4Position=0;
			for (int i=1;i<colNamesSA.size();i++)
			{
				String colName_naclSAIPV4Position = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable-subnetAssociations-subnetsGrid']/div/div/table/thead/tr/th)["+i+"]")).getText();
				if (colName_naclSAIPV4Position.equals("IPv4 CIDR"))
				{
					naclSAIPV4Position=i;
					break;
				}
			}

			List<WebElement> rowElementNACLSA = driver.findElements(By.xpath("//div[@id='gwt-debug-gridTable-subnetAssociations-subnetsGrid']//table/tbody/tr"));
			
			for (int NACLSA_CounterSecurity=1;NACLSA_CounterSecurity<=rowElementNACLSA.size();)
			{
				NACLSubnet = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable-subnetAssociations-subnetsGrid']//table/tbody/tr["+NACLSA_CounterSecurity+"]/td/div/a)["+naclSASubnetPosition+"]")).getText();
				NACLIPV4 = driver.findElement(By.xpath("(//div[@id='gwt-debug-gridTable-subnetAssociations-subnetsGrid']//table/tbody/tr["+NACLSA_CounterSecurity+"]/td/div)["+naclSAIPV4Position+"]")).getText();
				String[] dataToWrite1={NACLName,NACLSubnet,NACLIPV4}; 
				WriteExcel w1 = new WriteExcel();
				w1.writeExcel(excelFileWrite, "ExportExcel.xlsx", "NACLSubnetAssociations", dataToWrite1);
				NACLSA_CounterSecurity++;
			}
			NACLSubnetAssociations_rowCounterSecurity++;
		}
	}
}
*/