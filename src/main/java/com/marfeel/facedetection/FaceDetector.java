package com.marfeel.facedetection;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import java.util.ArrayList;
import java.util.Collections;

public class FaceDetector {

    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {


       // System.loadLibrary("libopencv_java300");
        System.out.println("\nRunning FaceDetector");

//        URL rsc = FaceDetector.class.getResource("/haarcascade_frontalface_alt.xml");
//        CascadeClassifier faceDetector = new CascadeClassifier(FaceDetector.class.getResource("/haarcascade_frontalface_alt.xml").getPath());
        ArrayList<CascadeClassifier> detectors = new ArrayList<CascadeClassifier>();
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_frontalface_default.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_frontalface_alt.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_frontalface_alt2.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_frontalface_alt_tree.xml").getPath()));
        //detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_fullbody.xml").getPath()));
        //detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_lowerbody.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_profileface.xml").getPath()));
        //detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_smile.xml").getPath()));
        //detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/haarcascades/haarcascade_upperbody.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/lbpcascades/lbpcascade_silverware.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/lbpcascades/lbpcascade_frontalface.xml").getPath()));
        detectors.add(new CascadeClassifier(FaceDetector.class.getResource("/lbpcascades/lbpcascade_profileface.xml").getPath()));

        for (Integer i=0; i<43; i++) {
            String filename = "/images/"+i.toString()+".jpg";

            Mat image = Highgui.imread(FaceDetector.class.getResource(filename).getPath());

            ArrayList<Rect> faceDetectionsAll = new ArrayList<Rect> ();
            for (CascadeClassifier detector: detectors) {
                applyDetector(detector, image, faceDetectionsAll);
            }
            System.out.println(String.format("Detected %s faces", faceDetectionsAll.toArray().length));

            for (Rect rect : faceDetectionsAll) {
                if (rect.width > image.width()*0.1 && rect.height > image.width()*0.1 ) {

                    Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));
                }
            }

            String outputfilename = i.toString()+".ouput.jpg";
            System.out.println(String.format("Writing %s", outputfilename));
            Highgui.imwrite(outputfilename, image);

        }
    }

    private static void applyDetector(CascadeClassifier faceDetector, Mat image, ArrayList<Rect> faceDetectionsAll) {
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        Collections.addAll(faceDetectionsAll, faceDetections.toArray());
    }

    public void faceDetection (){
        CascadeClassifier faceDetector = new CascadeClassifier(FaceDetector.class.getResource("/lbpcascades/lbpcascade_frontalface.xml").getPath());
        for (Integer i=0; i<21; i++) {
            String filename = "/images/"+i.toString()+".jpg";

            Mat image = Highgui.imread(FaceDetector.class.getResource(filename).getPath());

            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);

            System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

            for (Rect rect : faceDetections.toArray()) {
                Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));
            }

            String outputfilename = i.toString()+".ouput.jpg";
            System.out.println(String.format("Writing %s", outputfilename));
            Highgui.imwrite(outputfilename, image);

        }
    }

    public void faceDetectionWithFisherRecognizer () {
//        FaceRecognizer fr =
    }
}

