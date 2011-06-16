TARGET_FILES = report.pdf
SOURCE_FILES = $(wildcard **/*.latex)

.DEFAULT: all-no-logs
.SUFFIXES:
.PHONY: all all-no-logs clean util-clean log-clean template.pdf
.INTERMEDIATE: %.aux %.dvi %.nav %.out %.snm %.vrb
.PRECIOUS: %.toc %.log

all : $(TARGET_FILES) util-clean
all-no-logs : all log-clean

%.pdf : %.dvi
	dvipdf $< > $@

%.dvi : %.latex %.toc %.bbl
	echo "================ MAIN BUILD ==================="
	latex $<

%.toc : %.latex %.aux %.bbl
	latex $<

%.aux : %.latex $(SOURCE_FILES)
	latex $<

%.bbl : %.aux $(wildcard *.bib)
	bibtex $<

clean : util-clean log-clean
	rm -f $(TARGET_FILES)

util-clean :
	rm -f $(subst .pdf,.dvi,$(TARGET_FILES))
	rm -f $(subst .pdf,.out,$(TARGET_FILES))
	rm -f $(subst .pdf,.snm,$(TARGET_FILES))
	rm -f $(subst .pdf,.vrb,$(TARGET_FILES))
	rm -f $(subst .pdf,.nav,$(TARGET_FILES))

log-clean :
	rm -f $(subst .pdf,.log,$(TARGET_FILES))
	rm -f $(subst .pdf,.toc,$(TARGET_FILES))

