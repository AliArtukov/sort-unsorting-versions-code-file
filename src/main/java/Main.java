// Created by: Ali Artukov
// Created time: 21/12/2021 15:32
// Telegram: https://t.me/Ali_Artukov

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // Start me up

        long startTime = System.currentTimeMillis();
        System.out.println("Reading resources\\version.txt file.");
        System.out.println("Please wait a few seconds...");

        File versionFile = new File("C:\\Users\\OMEN\\Downloads\\Telegram Desktop\\sort-version-file\\sort-version-file\\src\\main\\resources\\version.txt");
        Scanner versionFileScanner = new Scanner(versionFile);
        List<String> versionList = new ArrayList<>();
        while (versionFileScanner.hasNext()) {
            versionList.add(versionFileScanner.nextLine());
        }

        String[] sortedVersionList = versionList.toArray(new String[0]);
        sort(sortedVersionList, 0, sortedVersionList.length - 1);

        File sortedVersionFile = new File("C:\\Users\\OMEN\\Downloads\\Telegram Desktop\\sort-version-file\\sort-version-file\\src\\main\\resources\\sorted_version.txt");
        sortedVersionFile.createNewFile();
        FileWriter sortedVersionFileWriter = new FileWriter(sortedVersionFile);
        sortedVersionFileWriter.write("SORTED FILES: " + sortedVersionList.length + "\n\n");
        for (String s : sortedVersionList) {
            sortedVersionFileWriter.write(s + "\n");
        }
        sortedVersionFileWriter.close();

        long endTime = System.currentTimeMillis();
        System.out.println("\n" + ((endTime - startTime) / 1000.0) + " seconds passed.\n");
        System.out.println("Read resources\\version.txt file and wrote a sorted list to resources\\sorted_version.txt file.");

    }

    public static void merge(String[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (!isFirstBig(L[i], R[j])) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void sort(String[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static boolean isFirstBig(String first, String second) {
        String[] firstList = first.split("\\.");
        String[] secondList = second.split("\\.");

        int minSize = 0;
        if (firstList.length < secondList.length) {
            minSize = firstList.length;
        } else {
            minSize = secondList.length;
        }

        for (int i = 0; i < minSize; i++) {
            if (Integer.parseInt(firstList[i]) > Integer.parseInt(secondList[i])) {
                return true;
            } else if (Integer.parseInt(firstList[i]) < Integer.parseInt(secondList[i])) {
                return false;
            }
        }

        if (firstList.length > minSize && checkForPositive(firstList, minSize)) {
            return true;
        } else if (secondList.length > minSize && checkForPositive(secondList, minSize)) {
            return false;
        }
        return false;
    }

    public static boolean checkForPositive(String[] arr, int startIndex) {
        for (int i = startIndex; i < arr.length; i++) {
            if (Integer.parseInt(arr[i]) > 0) {
                return true;
            }
        }
        return false;
    }

}
