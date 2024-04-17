@ECHO OFF

MD output

TYPE tests.txt
ECHO.
ECHO.

SET N=0
IF %1 NEQ [] (
	SET N=%1
	GOTO TEST
)

SET /P N=Melyik teszteset? (1-28 vagy * az összeshez)

:TEST
IF %N%==* GOTO ALL
IF %N% LSS 1 GOTO ERROR
IF %N% GTR 28 GOTO ERROR

:SELECT
java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -cp .\out\ Interpreter <.\input\%N%input.txt >.\output\%N%output.txt
ECHO ***** %N%. TESZTESET EREDMÉNY *****
FC .\output\%N%output.txt .\expected\%N%expected.txt
GOTO END

:ALL
ECHO Összes teszteset
FOR /L %%I IN (1,1,28) DO (
	java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -cp .\out\ Interpreter <.\input\%%Iinput.txt >.\output\%%Ioutput.txt
	ECHO ***** %%I. TESZTESET EREDMÉNY *****
	FC .\output\%%Ioutput.txt .\expected\%%Iexpected.txt
)
GOTO END

:ERROR
ECHO A megadott teszteset nem létezik!

:END