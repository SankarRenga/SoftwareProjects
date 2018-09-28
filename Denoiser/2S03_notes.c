/*  Conditional Expressions
      expr1?expr2:expr3
      i = 1, j = 2
      k = i>j? i:j;
      k is 2

    Logical Expressions
      (i + 1) < (k - j) --> same as i + 1 < k - j

      i = -10. j = -5, k =-1;
      (i<j)<k
      1 < -1 --> will return 0
      && --> and
      || --> or

      Loops
      i = 1
      while (i < n)
         i *= 2 --> i = 1, i = 2, i = 4, i = 8 , i = 16

     do
     {
        statements
     } while (expr);

    Break; --> Exit

    label:
    goto label:  --> jumps to 'label'

    Arrays
    int a[8]; --> 0-7 --> static array
    array indecies may be initialized to 0
    sizeof(a), sizeof(int) --> returns number of bytes

    Multidimensional Arrays
    int a[4][5]; --> 4x5 table of values
    First arg is for row and the second arg is for column

    Functions
    return-type function-name (parameters)
    {
      body
    }
    return type can be any data type (ex: int, char, float, void)
    parameters can be empty

    Passing 2D Arrays
    int sum2D (int a[][N], int n)
    {
      int i, j, sum = 0;
      for(i = 0; i < n; i++)
        for (j = 0; j < N; j++)
          sum += a[i][j];
      return sum
    }
    1D arrays pass size and arrays
    2D arrays passes second dimension

    Quick Sort
      Assume function
      void qsort (int a[], int low, int high)
      for an array of 9 elements
        --> qsort (a, 0, 8)
      Takes first number in array and shifts and compares it until it reaches a number higher than it or the end
        --> This number becomes the pivot
      Partition the sub-arrays until sorted

    Partition
      int partition (int a[], int p, int r)
        int i, j, k
        x = a[r]
        i = p - 1
        for (j = 0; j < r - 1; j++)
          if (a[j] <= x)
          {
            i++;
            swap a[i],a[j]
          }
        swap a[i + 1], a[r]
        return i + 1

    Object files
          Linking combines all object files into one files
          Never use #include to incorporate your header files
            header
              -Define
              -include
              -decleration
            -Function will be defined twice (Program won't know which one to use)
                -Specific for example with main.c, qsort.c, and split.c

  Makefiles
    Let's say current directory has main.c, split.c, qsort.c
    'make' will create main.o split.o qsort.o
    gcc -o program main.o qsort.o split.o --> Links files
    'make' looks into timestamp of files
        --> main.c created at 9 am, main.o created at 10 am
        --> 'make' will compare timestamps
            --> If .o file was modified after, 'make' will not do anything
            --> If .c file was modified after, 'make' will update .o file
    gcc -c  --> compiles Program
    cat -v -t -e makefile
      --> TAB shows as ctrl-I
      --> end of line shows as $
    make -f filename
    $@ --> Evaluates to current target
    $? prereq.
    Suffixes: .o .c
    >make executes program1
    make-p
    To build main.o
        --> $(CC) $(CFLAGS) -c main.c
        --> makefile could be 1 line --> program: main.o qsort.o split.o
    >make CFLAGS = -Wall
    >make CC = clang

    main.o: main.c
      --> $(CC) -c main.c  --> = gcc -c main.c
          CC = gcc
          > make CFLAGS = "-Wall -g"
          --> > make CC = icc
              now $(CC) -c main.c = icc -c main.c

    Pointers
      Each byte has a unique address
      Each variable occupies 1 or more bytes
      Pointers are variables that store an address
        --> int* p or int *p
        -->char "" ""
      ex:
        int a;    --> a stored somewhere in memory
        int *p;   --> points to locations in memory
        To intialize a Pointer
          --> p = &a;   --> & represents the address
        int b; <a = 10;
        b = *p --> dereferencing
        --> So now p points to a whcih points to b

        int *p;
        char *c;
        int a = 10;
        p = &a;
        char b = 'B';
        c = &b    --> Now c points to the address of b

        int a;
        a = 10;
        int *p = &a, *q = &a;
            --> p and q points to a (a has value of 10 stored)
        *p = 20;   --> p and q point to a which has stored value 20
        *q = 30    --> p and q """"  30
        p = q;     --> Nothing happens

        int a, b;
        a = 10; b = 10;
        int *p = &a, *q = &b    --> p points to a and q points to b
        p = q;                  --> p and q point to b

    Memory
      Local variable declared within
      Static memory
      Dynamic memory
            #include <stdlib.h>
            int n = 1000;
            int *p;
            p = malloc(N*sizeof(int));   -->memory allocation

            free(p); --> free memory

            if (p == NULL)
              print error message
      int a[N];
      int *p;
      p = a;
      p = p + 3
      *p = 5

      int *q = a + 7;

      int a = 1, b = 2
      void swap(int *p, int *q)
      {
        int t = *p;
        *p = *q;
        *q = t;
      }
      void swap(int *p, int *q)
      {
        int *t = p;
        p = q;
        q = t;
      }
      swap (&a, &b);   Proper --> will swap values
      swap (a, b);     --> mirage is a dun goof

      void max-min (int n, int *a, int *min, int *max)
      {
          int i;
          *min = a[0];
          *max = a[0];
          for (i = 0; i < n; i++)
          {
            if a[i] < *min
              *min = a[i];
          }
      }
      int a[n];
      int min, max;
      max-min(n, a, &min, &max);

    Memory & Pointers
      int a[10];
      int *b;
      b = a + 5;  --> b is address of a[5] --> Also can be written as b = &a[5];

      b[i] is equivalent to *(b + i)

      b[2] = 5;
      *(b + 2) = 5;

      b[-2] = 6
      b++; Increases pointer by one
      b[2] = 7

      int *b[3];  --> Array of Pointers
      int a[3][4];
      b[0] = a;           -->Address of a[0]
      b[1] = a + 2;       -->Address of a[0] + 2
      b[2] = b[0] + 5;    -->Address of b[0] + 5
      c = 10
      b[2] = &c           -->Address of c
      b[1][2]             -->Address that b[1] points to, then add 2

      int **a;    -->Equivalent to *(*a)
          (*a) --> pointer to array of ints
          *(*a)  --> pointer to array of pointers (usually for 2D arrays)
      a = malloc(n*sizeof(int *));
      for (i = 0; i < n; i++)
        a[i] = malloc(n*sizeof(int *));
      for (i = 0, i < n; i++)
        free(a[i]);    --> Deallocates memory
      free (a);

      int ***a;
        (*a) --> pointer to array of ints
        *(*a) --> pointer to array of Pointers
        *(*(*a)) --> pointer to pointer to array of pointers (usually for dealing with 3D arrays)
                 --> Points to the address of arrays where each index points to the address of ints

      int i = 3;
      int *j;
      int **k;
      j = &i;
      k = &j;
      -->k points to j which points to i
      print k  --> Gives address
      print *k  --> Gives address
      print **k  --> Gives the value of 3

      int a = 5, b = 10, c;
      int *p = &a, *q = &b;
      c = p - q;
        --> p points to a and q points to b
        --> the value of c is the difference between the 2 addressses
      c = *p - *q
        --> the value of c will be -5
      int d[20];
      p = &d[1];

      daxpy  --> computes ax + y, a scalar, x y vectors
      void daxpy (double a, int n, double *x, double *y, double *r)
      {
        int i;
        for (i = 0; i < n; i++)
          r[i] = a*x[i] + y[i]
      }
        Equivalent to
        *(r + i) = a*(*(x+i)) + *(y + i)
        *r++ = a*(*x++) + *y++
        -->Better performance

      Dynamic memory
        malloc
        calloc   -->Allocates + 0

    Program Organ
      Local variables
      -Inside function
      -automatic allocated on entry
      -deallocated on return

      Global variables
        //main.c
        #define N 10000
        int A[N]
        int main()
        {
          int max = max_array;
        }

        //max.array.c
        extern int A[];   --> Calls A[N]
        int max_array()
        {
          int max = 0;
          for ( )
            if (A[i] > max)
              max = A[i];
        }

        static int A[N];  --> A[N] can only be used in main file (extern wont work)

        static void fun1(){   --> Cant be used in other files
        }

        int i;      --> i1
        void f(int i)
        {
          i = 1;    --> i2
        }
        void g()
        {
          int i = 2; --> i3
          if (i > 0)
          {
            int i;   --> Lets call this i: i4
            i = 3;   --> refers to i4
          }
          i = 4;     -->changes i3
        }
        void h()
        {
          i = 5;  -->Changes i1
        }
        //Call function f
        f(i)  --> Doesn't affect i1
        void f(int *i)
        {
          *i = 1
        }
        f(&i)  --> changes i1

        //returning 2 values
        void min_max (int n, int *a. int *min, int *max)
        {
          //Insert code for finding min and max
          *min = ""
          *max = ""
        }

        Easier and more efficient to swap pointers than rows of data

      C program structure
        #include <directories>  --> searches in standard directory
        #include "           "  --> searches in current directory
        #define
        type definitions
        declarations of ext variables
        function declarations
        function definitions

    Midterm
          Understanding memory
          Indexing of 2D arrays
          Pointer arithmetic (swapping)
          Local vs global
          Makefiles

    Queue
        #define N 1000
        static int Q[N];
        int front = -1
        int end = -1
        void add_queue (int n)
        {
          if (front == -1 && end == -1)
          {
            front++;
            end++;
            Q[end] = n;
          }
          else
          {
            end++;
            Q[end] = n;
          }
        }

    Basic Types
        int - 32 bits
        short int - 16 bits
        long int - 32/64 bits
        unsigned int -2^32 - 1
        unsigned short
        unsigned long
        unsigned if you work with >= 0

        float - 32     -->precision 5-6
        double - 64    -->precision 15-16

    Strings
      "Hello" --> H e l l o \0
      char date[8] = "June_14";
      char *s = "Hello";
      -->s[1] = 'g';  --> ASCII
      char buf[80];
      scanf("%s", buf);
      -->To read line -- gets(buf);

      #include <string.h>
      char *strcpy (char *dest, const char *src);
      char dest[20];
      char src[] = "Hello World";
      strcpy (dest, src);

      void sum (int n, const double *A)
      {
        int n = 10;
        sum (n, A);
      }

      size_t strlem(const char*s)  --> Returns number of characters except for the last 0
      int strcmp (const char*s1, const char *s2)  --> Return <0 if S1 < S2
                                                  --> Return 0        = ab
                                                  --> Return >0       > ad


  Binary Trees
    7, 1, 15, 20, 20, 30 ,2
    All less than or equal goes in the left, and all greater than goes on the right (Starting with 7)  --> Binary Tree

  Linked List
    To add new element at the beginning, the first pointer points to new element which points to old one

  Structures
    --> Puts data together
    Ex: Struct {   --> Variables stored in order in memory (number (4 bytes) then name then on_hand (4 bytes)
          int number;   --> Part #
          char name [NAME_LEN + 1];  --> Name
          int on_hand;  --> Number of items
        } part1, part2;
        part1.number = 10;

   Ex: Struct part{
         int number;   --> Part #
         char name [NAME_LEN + 1];  --> Name
         int on_hand;  --> Number of items
       };
       struct part part1;
       struct part part2;
       part1.number = 10; --> Initializes number with 10
       part1.on_hand = 0; --> Initializes on_hand with 0
       part1.on_hand++;

   Ex: Struct point {
          float x, y, z;
      }
      Struct point A;
      A.x = 0;
      A.y = 1;
      A.z = 2;

  To avoid typing struct all the time
    typedef struct
    {
      float x, y;
    }Point;
    Point A, B;
    A.x = 0;
    A.y = 1;
    B = A;   --> Each part of B is equal to the corresponding component of A

    struct ListNode
    {
      int key;
      struct ListNode *next;
    };
    typedef struct ListNode *ListNodePtr;   -->Defines a pointer to the structure

    TreeNodePtr createBSTnode(int key)
      TreeNodePtr p = malloc(sizeof(struct TreeNode));
      p->key = key;       --> equivalent to p.key but for pointers
      p->left = p->right = NULL;
      return p;

    real time  --> Clock time
    user time  --> Time that CPU takes
    system time --> Time that goes into system operations (such as print statements)
        In command prompt:
          time ./primegap 2 100000  --> Shows user time

  Linked List
    struct node{
      int value;
      struct node *next;
    };
    struct node *first, *new_node
    new_node = malloc(sizeof(struct node));
    new_node->value = 10;  //Equivalent to (*new_node).value = 10;
    new_node->next = NULL:  //new_node points to 10, the next element is NULL or /0
    first = new_node; //points to first element which is 10
    //If you want to create a new node, repeat process
      new_node = malloc(sizeof(struct node));
      new_node->value = 20;  //Want to make it so the list is 20 --> 10 _--> Null, Must insert in the beginning of list
      new_node->next = first //new node points to 20 which points to 10 --> NULL
      first = new_node;

   Ex:
      -->10 --> 20 --> 30 --> 40 --> NULL
      search for 30 (Check if it is in array)
      Steps:
        Set up a pointer
        if (p->value == 30)
        if not p = p->next
        -->If the value doesn't exist, the p will eventually be equal to NULL
      Insert 25 into the list
        Will need 2 pointers p1 p2
        new_node->next = q  (Let's say q points to 30 at this point)
        p will equal to the value before 30
        p will point to new_node which will point to q

    singly linked lists --> Previous examples
    doubly linked lists
        Points forward and backward (next an previous)
        Takes a bit more memory

    Deallocation function
        free (new_node)

    TreeNode
      start with a key (ex: 7)
      lower than or equal key goes to the left
      great than or equal to key goes to right
      p->left->key
      p->key
      p->right->key

    Pointers to Pointers
      struct node *add-to-list (struct node *list, int n)
      {
        struct node *new_node = malloc(sizeof(struct node));
        new_node->value = n;
        new_node->next = list;
        return new_node;
      }
      list = add_to_list (list, n)                  -->list->n->...

      void add_to_list (struct node *list, int n)
      {
        struct new_node = malloc ()
        new_node->value = n;
        new_node->next = list;
      }
      add_to_list (list, n)   -->new_node->n->(pointer that list is also pointing to)  -->Possible memory leak
      -->Fix:
              void add_to_list (struct node *list, int n)
              {
                struct new_node = malloc ()
                new_node->value = n;
                new_node->next = *list;
                *list = new_node;
              }
              add_to_list (&list, n);


    #define OFFSET(i) ((char*)a + i*el_size)
    #define OFFSET(a,i,n)
    int a[10]
    double a[10]

    memmove(src, dest, num of bytes)   --> Moves data from one place to another
    #define COPY (a,b) memmove (a,b, el_size)   -->This statement kinda replaces the memmove function
                                                -->el_size will automatically be recognized (as long as var names are the same)
    double time = getTime();  -->Execution time
    printf ("qsorted in"  getTime() - time)

    void (*pf)(int) --> pointer to function that returns void and takes an integer
      --> To call --> pf(10)
    int *(*x[10])(void)
        --> *x[10] --> array of pointers to functions with no arguments that returns a pointer to an integer

  Ch 17 ex 16
    int sum (int (*f)(int), int start, int end)
    {
      int i, sum = 0;
      for  (i = start; i <= end; i++)
        sum += f(i);
      return sum;
    }

    int g(int i)
      {
        return 2*i;
      }

    s = sum(g, 0, 10);

    Can assign functions to pointers
    --> fcn x[4];
    x[0] = sin;
    x[1] = cos
    etc.

  Input/Output
    stdin -->standard Input
    stdout -->standard Output
    stderr --> standard error Output

    Text vs. Binary
      12345678910   -->When stored as text string, each character is stored as an ASCII values
      -->11 bytes   -->as binary double 8 bytes

    1. Open file
    2. Read/write
    3. Close file

    Reading
      FILE *f;
      f = fopen("test.dat", "r");  -->If file doesnt exist, return NULL
      fscanf (f, ...)   --> ... like in scanf
      fclose(f);

    Writing
      f = fopen ("test.dat", "w") -->If file already exists, overwrite or if its not the create it
      fprintf(f, ...)  --> ... like in fprintf
      fclose(f);

    typedef struct
    {
      //char city[30];  --> waste of memory, better to have argument as a pointer
      char *city;
      //char country[30];
      char *country;
      int populations;
    }City;
      sizeof(City) is 24 --> pointers are 8 bytes and int is 4 (With another 4 bytes )

    strtoken (buf, ",")  --> returns first string before the comma


  Software Optimizations
    Assume n particles with coordinates (x,y,z)
      double a[3][n]; OR double a[n][3] -->Which one is better?
        -->Second one (nx3)

    Common Subexpressions
      Assume
      double a,b,c,s1,s2
      s1 = a + b + c;
      s2 = a + b - c;  -->Optimizing compiler would do:
                          t = a + b
                          s1 = t + c
                          s2 = t -c

      Assume x is an array
      Consider
        r = x[i] * x[i];
        s = x[i] - 1;  --> Optimizing compiler would do: (x[i] would have to be retieved 3 times)
                           t = x[i];  --> Puts in register
                           r = t*t;
                           s = t - 1;

   Loop Invariant Motion
    for (i = 0; i < n; i++)
      a[i] = r*s*a[i]
    -->Optimized
        v = r*s;
        for (i = 0; i < n; i++)
          a[i] *= v;

  Bitwise operations
    -Low level programming, encryption, graphics, efficiency
    i = i<<3   -->shift i to the left by 3 bits (i = i*8) --> unsigned
    ~ --> complement
    & --> and
    | --> or
    ^ --> excusive XOR

    i = 21
    0000 0000 0001 0101
    j = ~ i  --> j = 1111 1111 1110 1010
    j = j&0x00FF;
    0x00FF = 0000 0000 1111 1111
        --> 1111 1111 1110 1010
            0000 0000 1111 1111
            0000 0000 1110 1010
    j = j|0x0100
        --> 0000 0000 1110 1010
            0000 0001 0000 1000
            0000 0001 1110 1010
    Check if even or odd --> Check last bit
      if (n%2 == 0)
       even
      else
       odd

      if (n&0x01)
       odd
      else
       even

    Loop Unrolling
      double dot(int n, double *a, double *b)
      {
        int i;
        double s = 0;
        for (i = 0; i < n; i++)
        {
          s += a[i]*b[i];
        }
        return s;
      }

     rem = n$0x3;
     for (i = 0; i < n - rem; i+=4)
     {
      s+=a[i]*b[i];
      s+=a[i+1]*b[i+1];
      s+=a[i+2]*b[i+2];
      s+=a[i+3]*b[i+3];
    }                  -->More efficient

    for (i = n - rem; i < n; i++)
    {
      s+=a[i]*b[i];
    }                  --> Part of the above loop

    A) for (int i = 0; i < n; i++)
        s += a[i]*b[i]
      -->Index counting occurs
    B) while (n--)
        s += *(a++) * *(b++);
      -->More efficient

  Counting number of bits in an int
    int wordLength()
      {
        int i;
        unsigned v = (unsigned)~0;   --> Will flip everthing to 1's
        for (i = 1, (v = v >> 1)>0; i++)
            ;
        return i;
      }                              -->If int is 8 bits
                                        1111 1111
                                        0111 1111
                                        0011 1111 and so on until it reaches 0

  The Preprocessor
    C Program -> Preprocessor -> Compiler
    #include < >
    #define N 10

    #define SQR(x)   x*x
    --> y = SQR (x + 1)
        y = x + 1*x + 1
    #define SQR(x) (x)*(x)
    --> y = SQR(x + 1)
        y = (x+1)*(x+1);

    -->Using #define for functions is more efficient
    -->Macro
        -->Efficiency
        -->No type checking
        -->Can lead to large code

   -->Can use other macros when defining and calling a macro

   #define A(i,j,N) A[i*N+j]
   -->double A[10]
      double d = A[1,2,3]  -->Will access A[1*3 + 2]

  Interface and implementation
    .h header file --> Interface
    .c  --> implementation
    -->Do not put variable declerations in a header files or function bodies
      --> extern int a;
      -->int a;

  Conditional Compilation
    #ifdef DEBUG
      print ()  -->If macro is defined, print statement
    #endif

    #ifndef   -->If macro is not defined

  Program Design
    Don't compare floating point for equality
      -->if (fabs(x-y) < 1e-12)
        if (x == y) -->program may never get here

        double x = 0.1       -->52 bits (mantissa is 52 bits)
        float y = x;  -->y won't be the exact same as x (matissa is 23 bits for a float)

        (x + y) + z = x + (y + z)  --> false
        (x*y)*z = x*(y*z) --> false

  Review
    Beginning stuff
    arrays --> addressing, single vs double Arrays
    Functions --> argument passing, local vs global variable
                  int *f()
                  {
                    int i;
                    return &i;
                  }
    Pointers
    Pointers and Arrays
    Strings
      --> copy a Strings
      --> concatenate 2 strings
    Structures
    Advanced use of Pointers
      --> linked list, trees
      --> struct A
          {
            int a;
            char b;  -->sometimes stored as 1 byte, sometimes 4 bytes (3 not used)
            int c
          }
    I/O --> Block I/O
        --> Understand how it works

  exam
    1) Stack, queues, linked lists, trees, variable types, function dec/def
    2) strings
    3) Bit operations
    4) 1D, 2D arrays, Pointers
    5) Program organization

    int main()
    {
      int i, j;
      int *p = &i;   --> Address if i stored in p
    }

    
*/
