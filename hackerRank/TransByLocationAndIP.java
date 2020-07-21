package hackerRank;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




class Result_trans {

	/*
	 * Complete the 'getTransactions' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts following parameters:
	 *  1. INTEGER userId
	 *  2. INTEGER locationId
	 *  3. INTEGER netStart
	 *  4. INTEGER netEnd
	 *
	 *  https://jsonmock.hackerrank.com/api/transactions/search?userId=
	 */

	public static int getTransactions(int userId, int locationId, int netStart, int netEnd) {
		
		double total=0.0;
		int total_rounded;
		String url = "https://jsonmock.hackerrank.com/api/transactions/search?userId="+userId;
		String pageno="&page=";
		long num_pages=1;
		for(int i=1;i<=num_pages;i++) {
			
			JSONObject response;
			try {
				response = request_data(url+pageno+i);
			
			if(response!=null) {
				if(i==1) {
					num_pages=(long) response.get("total_pages");
				}//update the num of pages
				JSONArray data=(JSONArray) response.get("data");
				for(int j=0;j<data.size();j++) {
					JSONObject data_item=(JSONObject) data.get(j);
					Map location=((Map)data_item.get("location"));
					long loc_Id= (long) location.get("id");
					String[] split = (data_item.get("ip").toString()).split("\\.",2);
					String ip=split[0];
					if(loc_Id==locationId&&(Integer.parseInt(ip)>=netStart)&&(Integer.parseInt(ip)<=netEnd)) {
						String amount=data_item.get("amount").toString().replaceAll("[$,]","");
						total=total+Double.parseDouble(amount);
						System.out.println("Current total"+total);
					}
				}
			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		total_rounded=(int) Math.round(total);
		return total_rounded;

	}

	public static JSONObject request_data(String url) throws ParseException {
		URL obj;
		
		JSONObject myResponse=null;
		try {
			obj = new URL(url);
			HttpsURLConnection con= (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//print in String
			//System.out.println(response.toString());
			//Read JSON response and print
			Object parsed_obj=new JSONParser().parse(response.toString());
			myResponse = (JSONObject) parsed_obj;

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myResponse; 


	}
	/*
	 * public static int Total_amt_currpage(JSONArray data) {
	 * 
	 * }
	 */

}

public class TransByLocationAndIP {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
		BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));


		int userId = Integer.parseInt(bufferedReader.readLine().trim());

		int locationId = Integer.parseInt(bufferedReader.readLine().trim());

		int netStart = Integer.parseInt(bufferedReader.readLine().trim());

		int netEnd = Integer.parseInt(bufferedReader.readLine().trim());

		int result = Result_trans.getTransactions(userId, locationId, netStart, netEnd);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
