package com.jordanweaver.reviewassignment;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.XmlStreamWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.XMLFormatter;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class DataBaseHelper {

    Context mContext;

    public DataBaseHelper(Context context){
        this.mContext = context;
    }



    public JSONArray loadJSONArray(){
        String data = null;
        JSONArray jsonNews = null;


        try {
            FileInputStream fis = mContext.openFileInput("NewsArticle.txt");
            data = IOUtils.toString(fis);
            jsonNews = new JSONArray(data);
            fis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsonNews == null){
            return jsonNews = new JSONArray();
        }

        return jsonNews;
    }



    public void saveJSONArray(JSONArray _news){

        try {
            FileOutputStream fos = mContext.openFileOutput("NewsArticle.txt", Context.MODE_PRIVATE);
            JSONArray saveArray = _news;
            fos.write(saveArray.toString().getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void createXML(){
//        XmlSerializer serializer = new XmlSerializer() {
//            @Override
//            public void setFeature(String name, boolean state) throws IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public boolean getFeature(String name) {
//                return false;
//            }
//
//            @Override
//            public void setProperty(String name, Object value) throws IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public Object getProperty(String name) {
//                return null;
//            }
//
//            @Override
//            public void setOutput(OutputStream os, String encoding) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void setOutput(Writer writer) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void startDocument(String encoding, Boolean standalone) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void endDocument() throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void setPrefix(String prefix, String namespace) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public String getPrefix(String namespace, boolean generatePrefix) throws IllegalArgumentException {
//                return null;
//            }
//
//            @Override
//            public int getDepth() {
//                return 0;
//            }
//
//            @Override
//            public String getNamespace() {
//                return null;
//            }
//
//            @Override
//            public String getName() {
//                return null;
//            }
//
//            @Override
//            public XmlSerializer startTag(String namespace, String name) throws IOException, IllegalArgumentException, IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public XmlSerializer attribute(String namespace, String name, String value) throws IOException, IllegalArgumentException, IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public XmlSerializer endTag(String namespace, String name) throws IOException, IllegalArgumentException, IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public XmlSerializer text(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public XmlSerializer text(char[] buf, int start, int len) throws IOException, IllegalArgumentException, IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public void cdsect(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void entityRef(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void processingInstruction(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void comment(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void docdecl(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void ignorableWhitespace(String text) throws IOException, IllegalArgumentException, IllegalStateException {
//
//            }
//
//            @Override
//            public void flush() throws IOException {
//
//            }
//        };

    //}

}
