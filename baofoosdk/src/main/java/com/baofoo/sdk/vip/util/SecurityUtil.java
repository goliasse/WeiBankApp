package com.baofoo.sdk.vip.util;
import android.annotation.SuppressLint;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加密类
 * 
 * @author yuqih
 * 
 */
public class SecurityUtil {

	/**
	 * md5签名，先用md5算法转化为byte数组，然后转化为hexString
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		return md5(str, "utf-8");
	}

	/**
	 * MD5加密函数
	 * 
	 * @param strTemp
	 * @return
	 */
	public static String md5(String str, String encode) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes(encode));
			byte[] digest = md5.digest();

			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				// byteVar &
				// 0x000000FF的作用是，如果digest[i]是负数，则会清除前面24个零，正的byte整型不受影响。
				// (...) | 0xFFFFFF00的作用是，如果digest[i]是正数，则置前24位为一，
				// 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
				strTemp = Integer.toHexString((digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}

			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException("MD5加密发生错误", e);
		}
	}

	/**
	 * DES加密
	 * 
	 * @param source
	 * @param desKey
	 * @return
	 */
	@SuppressLint("TrulyRandom")
	public static String desEncrypt(String source, String desKey) {
		try {
			// 从原始密匙数据创建DESKeySpec对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			// 现在，获取数据并加密
			byte[] destBytes = cipher.doFinal(source.getBytes());
			StringBuilder hexRetSB = new StringBuilder();
			for (byte b : destBytes) {
				String hexString = Integer.toHexString(0x00ff & b);
				hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
			}
			return hexRetSB.toString();
		} catch (Exception e) {
			throw new RuntimeException("DES加密发生错误", e);
		}
	}

	/**
	 * DES解密
	 * 
	 * @param source
	 * @param desKey
	 * @return
	 */
	public static String desDecrypt(String source, String desKey) {
		// 解密数据
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return desDecrypt(sourceBytes, desKey);
	}

	/**
	 * DES解密
	 * 
	 * @param source
	 * @param desKey
	 * @return
	 */
	public static String desDecrypt(byte[] source, String desKey) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			// 现在，获取数据并解密
			byte[] destBytes = cipher.doFinal(source);
			return new String(destBytes);
		} catch (Exception e) {
			throw new RuntimeException("DES解密发生错误", e);
		}
	}

}
