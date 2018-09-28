#include <stdio.h>
#include <stdlib.h>

#include "filter.h"
extern double getTime(int flag);

int main(int argc, char *argv[]) {

  if (argc < 5 )
    {
      printf("Invalid number of arguments");
      return 1;
    }

  char *input = argv[1];
  char *output = argv[2];
  int size = atoi (argv[3]);
  char *f = argv[4];

  filter ftype;
  if (f[0] == 'A')
    ftype = MEAN;
  else if (f[0] == 'M')
    ftype = MEDIAN;
  else
    {
      printf ("Invalid filter type");
      return 1;
    }


  int width;
  int height;
  int max;

  double time = getTime(1);
  RGB *infile;
  infile = readPPM (input, &width, &height, &max);
  printf ("Reading File\n");
  printf("Read file in %.1e seconds\n\n", getTime(1) - time);

  time = getTime(1);
  RGB *image;
  image = denoiseImage(width, height, infile, size, ftype);
  printf ("Processing %dx%d file using a %dx%d window with %s filter\n", width, height, size, size, f);
  printf("Processed file in %.1e seconds\n\n", getTime(1) - time);

  time = getTime(1);
  writePPM(output, width, height, max, image);
  printf ("Outputting File\n");
  printf("Outputted file in %.1e seconds\n\n", getTime(1) - time);

  free(image);
  free(infile);
  return 0;
}
