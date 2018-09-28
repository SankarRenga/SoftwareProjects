#include <stdio.h>
#include <stdlib.h>
#include "filter.h"

int cmp(const void *a, const void *b)
{
   return (*(int*)a - *(int*)b);
}

RGB *readPPM (const char *file, int *width, int *height, int *max)
{
  printf("%s\n", file);
  FILE *pic;
  pic = fopen (file, "r");

  fgetc(pic);
  fgetc(pic);

  fscanf (pic, "%d%d", width, height);
  fscanf (pic, "%d", max);

  int size = (*width)*(*height);
  RGB* pixels = malloc(sizeof(RGB)* size);
  for (int i = 0; i < size; i++)
  {
    fscanf (pic, "%hhu%hhu%hhu", &pixels[i].r, &pixels[i].g, &pixels[i].b);
  }
  fclose (pic);
  return pixels;
}

void writePPM (const char *file, int width, int height, int max,
const RGB *image)
{
   FILE *pic;
   pic = fopen(file, "w");

   //write the format header
   fprintf(pic, "P3\n");

   //Set the width and height of image
   fprintf(pic, "%d %d\n", width, height);

   //Set the max rgb value
   fprintf(pic, "%d\n", max);

   //Write the pixel values
   for (int i = 0; i < width*height; i++)
   {
     fprintf (pic, "%d %d %d ", image[i].r, image[i].g, image[i].b);
   }
   fclose(pic);
}

RGB *denoiseImage (int width, int height, const RGB *image, int n, filter f)
{
  int rad = n/2; //radius of sliding window
  RGB *mean = malloc(sizeof(RGB)*width*height);
  //Loop through every pixel
  for (int i = 0; i < height; i++)
  {
    for (int j = 0; j < width; j++)
    {
      //Find current position
      int x_position = j;
      int y_position = i;
      int x_win_size = rad;
      int y_win_size = rad;

      for (int m = 1; m <= rad; m++)
      {
        //Traverse towards the left of the window if possible
        if (j - m >= 0)
        {
          x_position = j - m;
          x_win_size = rad + 1;
        }
        //Traverse towards the top of the window if possible
        if (i - m >= 0)
        {
          y_position = i - m;
          y_win_size = rad + 1;
        }
      }

      //Mean filtering
      if (f == MEAN)
      {
        int red = 0, green = 0, blue = 0;
        int sum = 0;

        //Loop through every pixel in the window
        for (int y = y_position; y <= y_position + y_win_size; y++)
        {
          for (int x = x_position; x <= x_position + x_win_size; x++)
          {
            if (x >= width || y >= height)  //Skip iteration if outside image
              continue;
            //Sum up each individual colour
            red += image[y*width + x].r;
            green += image[y*width + x].g;
            blue += image[y*width + x].b;
            sum++;
          }
        }

        //Calculate mean for each colour and round to nearest int
        mean[i*width + j].r = (red+0.5)/sum;
        mean[i*width + j].g = (green+0.5)/sum;
        mean[i*width + j].b = (blue+0.5)/sum;
      }
      //Medium filtering
      else
      {
        int *red = malloc(sizeof(int)*width*height);
        int *green = malloc(sizeof(int)*width*height);
        int *blue = malloc(sizeof(int)*width*height);

        int counter = 0;
        //Loop through every pixel in the window
        for (int y = y_position; y <= y_position + y_win_size; y++)
        {
          for (int x = x_position; x <= x_position + x_win_size; x++)
          {
            if (x >= width || y >= height) //Skip iteration if outside image
              continue;
            //Store each colour into array
            red[counter] = image[y*width + x].r;
            green[counter] = image[y*width + x].g;
            blue[counter] = image[y*width + x].b;
            counter++;
          }
        }

        //Sort the values of the colours from smallest to largest
        qsort (red, counter, sizeof(int), cmp);
        qsort (green, counter, sizeof(int), cmp);
        qsort (blue, counter, sizeof(int), cmp);

        int mid = counter/2;
        if (counter%2 != 0)
        {
          //Assign the current pixel the median values if the window is of odd size
          mean[i*width + j].r = red[mid];
          mean[i*width + j].g = green[mid];
          mean[i*width + j].b = blue[mid];
        }
        else
        {
          //Assign the current pixel the median values if the window is of odd size
          mean[i*width + j].r = (red[mid] + red[mid-1])/2;
          mean[i*width + j].g = (green[mid] + green[mid-1])/2;
          mean[i*width + j].b = (blue[mid] + blue[mid-1])/2;
        }

        free(red);
        free(green);
        free(blue);
      }
    }
  }
  return mean;
}
