# Nokia Bounce Decomp

## Building

It builds with `gradlew build` but there is no point in that at the moment since I didn't implement any wrappers for MIDlet and Nokia APIs.

## Tools

### extract_locales

`extract_locales.py` -- extracts locales from `lang.***` files.

#### Requirements

 * Python 3 with PIP

#### Usage

```
pip install -r requirements.txt
python extract_locales.py
```

Replace `pip` and `python` with `pip3` and `python3` if your system has Python 2 installed.