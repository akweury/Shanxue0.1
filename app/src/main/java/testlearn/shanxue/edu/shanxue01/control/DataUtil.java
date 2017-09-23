package testlearn.shanxue.edu.shanxue01.control;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import testlearn.shanxue.edu.shanxue01.models.RhesisModel;
import testlearn.shanxue.edu.shanxue01.models.UserInfoModle;
import testlearn.shanxue.edu.shanxue01.models.UserLearnRecordModel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataUtil {
    private static final String TAG = "control.DataUtil";
    private static final String REQUESTTAG = "string request first";


    private static RequestQueue mRequestQueue ;
    private static StringRequest stringRequest;
    private DiskBasedCache mCache;
    private com.android.volley.Network mNetwork;


    public static String downloadJson(String[] urls) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            Log.i(TAG, "下载文件中...");
            URL url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();

            if (finalJson != null && finalJson.startsWith("\ufeff")) {
                finalJson = finalJson.substring(1);
            }


            Log.i(TAG, "下载结束，返回结果！");
            return finalJson;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Object loadData(String fileName) {
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileUtil.FOLDER_URL + File.separator + fileName));
            if (fileName == FileUtil.FILE_NAME_USER) {

                Object object = ois.readObject();
                ois.close();
                UserInfoModle userInfoModle = (UserInfoModle) object;
                Log.i(TAG, "用户信息获取完毕, 用户名: " + userInfoModle.getNickName());
                return object;
            } else if (fileName == FileUtil.FILE_NAME_RHESIS) {
                Object object = ois.readObject();
                ois.close();
                List<RhesisModel> rhesisModelList = ((List<RhesisModel>) object);
                Log.i(TAG, "词条信息读取完毕，第一个词条为： " + rhesisModelList.get(0).getRhesis_title());
                return object;
            } else if (fileName == FileUtil.FILE_NAME_USER_LEARN_RECORD){
                Object object = ois.readObject();
                ois.close();
                List<UserLearnRecordModel> userLearnRecordModelList = ((List<UserLearnRecordModel>)object);
                Log.i(TAG,"用户学习纪录信息读取完毕，第一个词条 node 为： " + userLearnRecordModelList.get(0).getStudy_node());
                return object;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public static String uploadJson(String[] urls,StringBuffer stringBuffer){
//
//
//
//        HttpURLConnection connection = null;
//        BufferedWriter writer = null;
//
//        try {
//            Log.i(TAG,"上传文件中...");
//            URL url = new URL(urls[0]);
//            connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.connect();
//            OutputStream stream = connection.getOutputStream();
//            writer = new BufferedWriter(new OutputStreamWriter(stream,"UTF-8"));
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//        try
//        {
//            URL u = new URL(urls[0]);
//
//            // Open the connection and prepare to POST
//            URLConnection uc = u.openConnection();
//            uc.setDoOutput(true);
//            uc.setDoInput(true);
//            uc.setAllowUserInteraction(false);
//
//            DataOutputStream dstream = new DataOutputStream(uc.getOutputStream());
//
//            // The POST line
//            dstream.writeBytes(String.valueOf(stringBuffer));
//            dstream.close();
//
//            // Read Response
//            InputStream in = uc.getInputStream();
//            int x;
//            while ( (x = in.read()) != -1)
//            {
//                System.out.write(x);
//            }
//            in.close();
//
//            BufferedReader r = new BufferedReader(new InputStreamReader(in));
//            StringBuffer buf = new StringBuffer();
//            String line;
//            while ((line = r.readLine())!=null) {
//                buf.append(line);
//            }
//
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();	// should do real exception handling
//        }
//
//        return null;
//    }



    public static class UploadTask extends AsyncTask<String, String, Integer> implements Serializable{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "uploadTask onPreExecute");
        }

        @Override
        protected Integer doInBackground(String... urls) {
            Log.i(TAG, "uploadTask doInBackground");
            int serverResponseCode = 0;

            HttpURLConnection connection;
            DataOutputStream dataOutputStream;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";


            int bytesRead,bytesAvailable,bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;

            String selectedFilePath = urls[0];
            File selectedFile = new File(selectedFilePath);


            String[] parts = selectedFilePath.split("/");
            final String fileName = parts[parts.length-1];


            try{
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(FileUtil.URL_UPLOAD_LEARN_RECORD);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();


                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


                return serverResponseCode;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            int returnCode = integer;
        }
    }

    public static int UploadFile(final String uploadFileName,final String uploadURL){

        String selectedFilePath = FileUtil.FOLDER_URL + File.separator + uploadFileName;

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];


        try{
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            URL url = new URL(uploadURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);//Allow Inputs
            connection.setDoOutput(true);//Allow Outputs
            connection.setUseCaches(false);//Don't use a cached Copy
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("ENCTYPE", "multipart/form-data");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setRequestProperty("uploaded_file",selectedFilePath);

            //creating new dataoutputstream
            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            //writing bytes to data outputstream
            dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + selectedFilePath + "\"" + lineEnd);

            dataOutputStream.writeBytes(lineEnd);

            //returns no. of bytes present in fileInputStream
            bytesAvailable = fileInputStream.available();
            //selecting the buffer size as minimum of available bytes or 1 MB
            bufferSize = Math.min(bytesAvailable,maxBufferSize);
            //setting the buffer as byte array of size of bufferSize
            buffer = new byte[bufferSize];

            //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
            bytesRead = fileInputStream.read(buffer,0,bufferSize);

            //loop repeats till bytesRead = -1, i.e., no bytes are left to read
            while (bytesRead > 0){
                //write the bytes read from inputstream
                dataOutputStream.write(buffer,0,bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                bytesRead = fileInputStream.read(buffer,0,bufferSize);
            }

            dataOutputStream.writeBytes(lineEnd);
            dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();


            //closing the input and output streams
            fileInputStream.close();
            dataOutputStream.flush();
            dataOutputStream.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponseCode;
    }

    public static void uploadData(List<UserLearnRecordModel> userLearnRecordModelList) {




        FileUtil.writeObj2File(userLearnRecordModelList,FileUtil.FILE_NAME_USER_LEARN_RECORD);

        String string = null;
        Gson gson = new Gson();
        //transfer learn-record-list to json
        for(int i=0;i<userLearnRecordModelList.size();i++){
            string += gson.toJson(userLearnRecordModelList.get(i));
        }
        //upload to web server
        UploadTask uploadTask= new UploadTask();
        uploadTask.execute(FileUtil.FILE_URL_USER_LEARN_RECORD);

    }

    public static void sendRequestAndPrintResponse(Activity activity) {

//        mRequestQueue = Volley.newRequestQueue(getActivity());

//        mCache = new DiskBasedCache(getActivity().getCacheDir(),4*1024*1024);

//        mNetwork = new BasicNetwork(new HurlStack());

//        mRequestQueue = new RequestQueue(mCache,mNetwork);

//        mRequestQueue.start();

        mRequestQueue = VolleySingleton.getInstance(activity.getApplicationContext()).getRequestQueue(activity.getApplicationContext());



        String url = FileUtil.URL_USERS + "?ID=" + "1" + "&book_ID=" + "1" + "&study_num=" + "13";

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error: " + error.toString());
            }
        });
        stringRequest.setTag(REQUESTTAG);

        mRequestQueue.add(stringRequest);


    }

    public static void sendPost(final String postString, final Activity activity){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, FileUtil.URL_TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(activity.getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                Log.i(TAG,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,error+"",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("json",postString);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);

    }




}
