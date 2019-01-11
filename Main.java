package Imagecode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String[] Massage;
            Massage = new String[input.length()];
            Massage[0] = Integer.toBinaryString(input.length());
            for (int i = 0; i < input.length(); i++) {
                char cha = input.charAt(i);
                int num = (int) cha;
                Massage[i] = Integer.toBinaryString(num);
            }
        BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Саша\\IdeaProjects\\Imagecode\\src\\Imagecode\\myfile.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        int j = 0;
        byte[] kostil_1 = new byte[imageInByte.length];
        for(int i = 0;i < Massage.length;i++){
            String binstr = Integer.toBinaryString((imageInByte[j] & 0xFF) + 0x100).substring(1);
            String codedstr = binstr.substring(0,binstr.length()-2) + Massage[i];
            byte[] codedstrbyte = new BigInteger(codedstr,2).toByteArray();
            System.arraycopy(kostil_1, 0, Arrays.copyOfRange(imageInByte,0,j+1), 0, j+1);
            System.arraycopy(codedstrbyte,0,kostil_1,0,codedstrbyte.length);
            System.arraycopy(kostil_1,0,Arrays.copyOfRange(imageInByte,j+1,imageInByte.length),0,imageInByte.length-Massage.length);
            j++;
        }
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(kostil_1));

        System.out.println("all went good");

        }


    }
