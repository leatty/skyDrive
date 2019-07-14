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
    
    
	
		
		//获取拓展名
		public static String getExtName(String fileName) {
			int idx = fileName.lastIndexOf("."); 
			String extions = fileName.substring(idx);
			return extions;
				
		}
	
	
		//解决文件重名
		public static String getUuidFileName(String fileName) {
			int idx = fileName.lastIndexOf("."); 
			String extions = fileName.substring(idx);
			return UUID.randomUUID().toString().replace("-", "")  + extions;
			
		}
		
		
		//目录分离
		public static String getPath(String uuidFileName) {
			int code1 = uuidFileName.hashCode();
			int d1 = code1 & 0xf;//作为一个目录
			int code2 = code1 >>> 4;
			int d2 = code2 & 0xf;//作为二级目录
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
                    // 关闭流产生的错误一般都可以忽略
                }
            }
        }
 
    }
 
    /**
     * MD5校验字符串
     * @param s String to be MD5
     * @return 'null' if cannot get MessageDigest
     */
    
    private static String getStringMD5( String s) {
        MessageDigest mdInst;
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
 
        byte[] btInput = s.getBytes();
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
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
            // NOTE: padding字符填充在字符串的右侧，和服务器的算法是一致的
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
     * 计算MD5校验
     * @param buffer
     * @return 空串，如果无法获得 MessageDigest实例
     */
    
    private static String MD5(ByteBuffer buffer) {
        String s = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>>,
                // 逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    /**  
         * 从服务器上 删除文件  
     * @param fileName 文件名  
     * @return true: 从服务器上删除成功   false:否则失败  
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
     * 生成4位数字字符串
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
     * 获取分类类型list<String>
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
