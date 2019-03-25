package com.bfranks.mvvm.util;

public class MovieUtil {
  public static String imagePathBuilder(String imagePath) {
    return "https://image.tmdb.org/t/p/" +
        "w500" +
        imagePath;
  }
}
