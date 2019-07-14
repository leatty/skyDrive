package xin.trooper.storage.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class StorageUtils {


	private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};
    
    
	
		
		//��ȡ��չ��
		public static String getExtName(String fileName) {
			int idx = fileName.lastIndexOf("."); 
			String extions = fileName.substring(idx);
			return extions;
				
		}
	
	
		//����ļ�����
		public static String getUuidFileName(String fileName) {
			int idx = fileName.lastIndexOf("."); 
			String extions = fileName.substring(idx);
			return UUID.randomUUID().toString().replace("-", "")  + extions;
			
		}
		
		
		//Ŀ¼����
		public static String getPath(String uuidFileName) {
			int code1 = uuidFileName.hashCode();
			int d1 = code1 & 0xf;//��Ϊһ��Ŀ¼
			int code2 = code1 >>> 4;
			int d2 = code2 & 0xf;//��Ϊ����Ŀ¼
			return "/" + d1 + "/" + d2; 
		}
		
		public static void main(String[] args) {
			System.out.println(getUuidFileName("aa.txt"));
		}
	
	
	
	
	
	
	
	
	
 
    /**
     * Get MD5 of a file (lower case)
     * @return empty string if I/O error when get MD5
     */
    public static String getFileMD5( File file) {
 
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            return MD5(ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length()));
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // �ر��������Ĵ���һ�㶼���Ժ���
                }
            }
        }
 
    }
 
    /**
     * MD5У���ַ���
     * @param s String to be MD5
     * @return 'null' if cannot get MessageDigest
     */
    
    private static String getStringMD5( String s) {
        MessageDigest mdInst;
        try {
            // ���MD5ժҪ�㷨�� MessageDigest ����
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
 
        byte[] btInput = s.getBytes();
        // ʹ��ָ�����ֽڸ���ժҪ
        mdInst.update(btInput);
        // �������
        byte[] md = mdInst.digest();
        // ������ת����ʮ�����Ƶ��ַ�����ʽ
        int length = md.length;
        char str[] = new char[length * 2];
        int k = 0;
        for (byte b : md) {
            str[k++] = hexDigits[b >>> 4 & 0xf];
            str[k++] = hexDigits[b & 0xf];
        }
        return new String(str);
    }
 
    
    @SuppressWarnings("unused")
	private static String getSubStr( String str, int subNu, char replace) {
        int length = str.length();
        if (length > subNu) {
            str = str.substring(length - subNu, length);
        } else if (length < subNu) {
            // NOTE: padding�ַ�������ַ������Ҳ࣬�ͷ��������㷨��һ�µ�
            str += createPaddingString(subNu - length, replace);
        }
        return str;
    }
 
    
    private static String createPaddingString(int n, char pad) {
        if (n <= 0) {
            return "";
        }
 
        char[] paddingArray = new char[n];
        Arrays.fill(paddingArray, pad);
        return new String(paddingArray);
    }
 
    /**
     * ����MD5У��
     * @param buffer
     * @return �մ�������޷���� MessageDigestʵ��
     */
    
    private static String MD5(ByteBuffer buffer) {
        String s = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            byte tmp[] = md.digest(); // MD5 �ļ�������һ�� 128 λ�ĳ�������
            // ���ֽڱ�ʾ���� 16 ���ֽ�
            char str[] = new char[16 * 2]; // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
            // ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
            int k = 0; // ��ʾת������ж�Ӧ���ַ�λ��
            for (int i = 0; i < 16; i++) { // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
                // ת���� 16 �����ַ���ת��
                byte byte0 = tmp[i]; // ȡ�� i ���ֽ�
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4 λ������ת��, >>>,
                // �߼����ƣ�������λһ������
                str[k++] = hexDigits[byte0 & 0xf]; // ȡ�ֽ��е� 4 λ������ת��
            }
            s = new String(str); // ����Ľ��ת��Ϊ�ַ���
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    /**  
         * �ӷ������� ɾ���ļ�  
     * @param fileName �ļ���  
     * @return true: �ӷ�������ɾ���ɹ�   false:����ʧ��  
     */  
    public boolean deleteStorageFile(String fileName){
    	Boolean result = null;
    	System.out.println("fileName = " + fileName);
        File file=new File(fileName);   
        if(file.exists()){ 
        	System.out.println("exist");
        	result =  file.delete();   
        }else {
        	System.out.println("not exist");
        }   
        return result;   
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * ����4λ�����ַ���
     */
    public static String getRandomPassword(){
		String val = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

    
    
    
    
    
    
    /**
     * 
     * ��ȡ��������list<String>
     */
    public static List<String> getClassifyType(String type) {
    	List<String> image = new ArrayList<String>();
    	image.add(".jpg");
    	image.add(".jpeg");
    	image.add(".png");
    	image.add(".gif");
    	image.add(".bmp");
    	image.add(".tif");
    	image.add(".raw");
    	List<String> document = new ArrayList<String>();
    	document.add(".doc");
    	document.add(".ppt");
    	document.add(".xls");
    	document.add(".vsd");
    	document.add(".txt");
    	document.add(".js");
    	document.add(".pdf");
    	document.add(".docx");
    	document.add(".pptx");
    	document.add(".xlsx");
    	document.add(".vsdx");
    	List<String> video = new ArrayList<String>();
    	video.add(".mpeg");
    	video.add(".avi");
    	video.add(".mov");
    	video.add(".asf");
    	video.add(".wmv");
    	video.add(".mkv");
    	video.add(".flv");
    	video.add(".rmvb");
    	video.add(".mp4");
    	List<String> music = new ArrayList<String>();
    	music.add(".mp3");
    	music.add(".wav");
    	music.add(".ape");
    	music.add(".flac");
    	music.add(".aac");
    	List<String> list = new ArrayList<String>();
    	if(type.equals("1")) {
    		list = image;
    	}else if(type.equals("2")) {
    		list = document;
    	}else if(type.equals("3")) {
    		list = video;
    	}else if(type.equals("4")) {
    		list = music;
    	}
    	return list;
    }
    
    
    
    
    
}
