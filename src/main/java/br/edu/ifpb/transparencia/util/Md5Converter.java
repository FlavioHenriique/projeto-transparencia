package br.edu.ifpb.transparencia.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Converter {

    public static String convertToMd5(String string){
        return DigestUtils
                .md5Hex(string).toUpperCase();
    }
}
