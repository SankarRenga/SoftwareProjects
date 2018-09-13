LOC = doc/spec

doc: .FORCE
	for i in $(LOC)/*.tex; do pdflatex -draftmode -output-directory $(LOC) "$$i"; done
	for i in $(LOC)/*.tex; do pdflatex -output-directory $(LOC) "$$i"; done
	make clean
	
clean:
	rm -f $(LOC)/*.aux
	rm -f $(LOC)/*.log
	rm -f $(LOC)/*.toc
	rm -f $(LOC)/*.gz
	
.FORCE:
