TARGET_FILES = report.pdf
SOURCE_FILES = $(wildcard **/*.latex)

.DEFAULT: all
.SUFFIXES:
.PHONY: all no-logs clean log-clean
.INTERMEDIATE: %.aux %.bbl %.dvi %.ptmp %.toc
.PRECIOUS: %.log %.blg

all : $(TARGET_FILES)
no-logs : all log-clean

%.pdf : %.dvi
	echo "========== $@ ==========="
	dvipdf $< > $@
	rm -f $(subst .dvi,.aux,$<)

%.dvi : %.latex %.toc %.bbl
	echo "========== $@ ==========="
	latex $<
	latex $<

%.toc : %.latex %.ptmp
	echo "========== $@ ==========="
	latex $<

%.ptmp : %.latex $(SOURCE_FILES)
	echo "========== $@ ==========="
	latex $<
	touch $@

%.blg %.bbl : %.ptmp $(wildcard *.bib)
	echo "========== $@ ==========="
	bibtex $(subst .ptmp,.aux,$<)

clean : log-clean
	echo "========== $@ ==========="
	rm -f $(TARGET_FILES)

log-clean :
	echo "========== $@ ==========="
	rm -f $(subst .pdf,.blg,$(TARGET_FILES))
	rm -f $(subst .pdf,.log,$(TARGET_FILES))
	rm -f $(subst .dvi,.aux,$(TARGET_FILES))

