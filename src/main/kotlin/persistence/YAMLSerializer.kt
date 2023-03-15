package persistence

import models.Note
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class YAMLSerializer(private val file: File) : Serializer {
    @Throws(Exception::class)
    override fun read(): Any {
        val yaml = Yaml()
        val reader = FileReader(file)
        val obj = yaml.load(reader) as Any
        reader.close()
        return obj
    }

    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val yaml = Yaml()
        val writer = FileWriter(file)
        yaml.dump(obj, writer)
        writer.close()
    }
}