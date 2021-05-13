/*package Libraries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import com.google.common.io.Files;

import io.restassured.mapper.ObjectMapper;


public class Excel2Json 
{
	public static void main(String ar[]) throws Exception
	{
		writeJsonSimpleDemo("C:\\Users\\S00297\\workspace\\airbus\\src\\test\\java\\restAPI\\test.json");
	}
	
	public static void writeJsonSimpleDemo(String filename) throws Exception 
	{
	    JSONObject sampleObject = new JSONObject();
	    
	    "action": "order",
	    "dialog_param_HostName": "de2tt1-cpRest",
	    "dialog_limit": "localhost",
	    "dialog_param_requestingUser": "iid_891264",
	    "dialog_param_service_name": "de2tt1-cpRest",
	    "dialog_param_regionid": "DE2",
	    "dialog_param_regionName": "G-CLF-REG-DE2",
	    "dialog_param_tenantName": "G-CLF-TEN-DE2-TT1",
	    "dialog_param_tenantID": "G-CLF-TEN-DE2-TT1",
	    "dialog_param_ServerModel":"Standard_A2_v2",
	    "dialog_param_image": "RedHat-7.6",
	    "dialog_param_netZone": "NetZone1",
	    "dialog_param_TargetPlatform": "Hyper-V",
	    "dialog_param_CloudName": "Tenant1_Oasis",
	    "dialog_param_imageOSType": "Linux",
	    "dialog_param_imageAzureOffer": "RHEL-Vanilla-7.6",
	    "dialog_param_imageAzurePublisher": "RedHat",
	    "dialog_param_imageAzureSku": "RedHat-7.6",
	    "dialog_param_new_availability_set": "null",
	    "dialog_param_existing_availability_set": "null",
	    "dialog_param_computeClass": "Gold",
	    "dialog_param_storageClass": "Gold",
	    "dialog_param_domain_name": "oasis.dev2.infinity.common.airbusds.corp",
	    "dialog_param_VMM_VMSubnetName": "VMNET_Tenant1_Oasis-frontend",
	    "dialog_param_VMM_ServerName":"DE2MGMTSVMM002.MGMT.infinity.de.airbusds.corp",
	    "dialog_param_VMM_VMNetworkName": "VMNET_Tenant1_Oasis",
	    "dialog_param_second_disk": "1",
	    "select_availability_set": "none",
	    "Confirmation": "t",
	    "param_agreement": "infinity customer agreement"
	    
	    HashMap<String,String> hm=new HashMap<String,String>();
	    
	    
	    hm.put("dialog_param_HostName", "test1");
	    hm.put("dialog_param_image", "Windows");
	    hm.put("Confirmation","t");
	    hm.put("param_agreement", "infinity customer agreement");
	    hm.put("action", "order");
	    
	    Collections.sort((List<T>) hm);
	    
	    hm
	    
	    sampleObject.put("action", "order");
	    sampleObject.put("dialog_param_HostName", "test1");
	    sampleObject.put("dialog_param_image", "Windows");
	    sampleObject.put("Confirmation","t");
	    sampleObject.put("param_agreement", "infinity customer agreement");
	    

	    JSONArray messages = new JSONArray();
	    messages.add("Hey!");
	    messages.add("What's up?!");

	    sampleObject.put("messages", messages);
	    Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
	    //Files.write(Paths.get(filename), hm.toString().getBytes());
	}
	
}*/