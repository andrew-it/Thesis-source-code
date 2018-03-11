/** TODO
 * 1. Разделять на файлы: вроде c и h
 * 2. Добавлять (include) эти файлы в Main
 * 3. В каждую функцию добавлять указатель на this (self)
 *
 * =========== TYPE_ALIAS.h ===========
 * #ifndef TYPE_ALIAS
 * #define TYPE_ALIAS
 *
 * typedef struct {
 *      Super_type super;
 *      params...
 * } Class_alias;
 *
 * void TYPE_ALIAS_constructor(self_pointer, args..);
 *
 * ret_type TYPE_ALIAS_func_name(self_pointer, args..) ;
 *
 * #endif
 *
 * =========== TYPE_ALIAS.c ===========
 * #include "TYPE_ALIAS.h"
 *
 * void TYPE_ALIAS_constructor(self_pointer, args..) {
 *      body...
 * }
 *
 * ret_type TYPE_ALIAS_func_name(self_pointer, args..) {
 *      body...
 * }
 **/

class Class(lexeme: Lexemes) : SourceCode {
    // FIXME !!
    override var filename = lexeme.lexemes[EXPR_TYPES.TYPE_ALIAS]!!

    val header = _HeaderFile(lexeme, filename)
    val source = _ClassFile(lexeme, filename)
    override fun construct(): String {
        return textWrap(filename.construct() + ".c") + "\n" + source.construct() +
                textWrap(filename.construct() + ".h") + "\n" + header.construct()
    }

}
