CFLAGS = -g -pg -std=c99
OBJS = main_denoise.o denoise.o timing.o

denoise: $(OBJS)
	$(CC) $(CFLAGS) -o $@ $^

clean:
	rm -f $(OBJS) denoise
