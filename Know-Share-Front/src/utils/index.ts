// 目录
export interface TOCEntry {
  level: number;
  text: string;
}

//随机生成RGB颜色
export const randomRgbaColor = () => {
  var r = Math.floor(Math.random() * 256); //随机生成256以内r值
  var g = Math.floor(Math.random() * 256); //随机生成256以内g值
  var b = Math.floor(Math.random() * 256); //随机生成256以内b值
  return `rgb(${r},${g},${b})`; //返回rgba(r,g,b,a)格式颜色
};

// 从HTML文本中获取目录
export const getTitle = (htmlString: string): TOCEntry[] => {
  // Parse the HTML string into a DOM object
  const parser = new DOMParser();
  const doc = parser.parseFromString(htmlString, "text/html");

  // Define a regular expression to match header tags (h1, h2, etc.)
  const headerRegex: RegExp = /^h[1-6]$/i;

  // Function to extract text content from header elements
  function extractHeaderText(headerElement: Element): string {
    return headerElement.textContent?.trim() || "";
  }

  // Function to generate a table of contents (TOC) from the parsed DOM object
  function generateTOC(parsedDoc: Document): TOCEntry[] {
    const toc: TOCEntry[] = [];
    const headerElements = parsedDoc.querySelectorAll("h1, h2, h3, h4, h5, h6");

    headerElements.forEach((headerElement) => {
      const headerLevel = parseInt(headerElement.tagName.charAt(1), 10); // Extract the header level
      const headerText = extractHeaderText(headerElement);

      // Create an object representing a TOC entry
      const tocEntry: TOCEntry = {
        level: headerLevel,
        text: headerText,
      };

      toc.push(tocEntry);
    });

    return toc;
  }

  // Generate and log the table of contents
  const tableOfContents: TOCEntry[] = generateTOC(doc);
  return tableOfContents;
};

// 时间转换 转换为yyyy-MM-dd形式
export const parseTime = (timeString: string): string => {
  return (
    new Date(timeString).getFullYear() +
    "-" +
    (new Date(timeString).getMonth() + 1 > 10
      ? new Date(timeString).getMonth() + 1
      : "0" + (new Date(timeString).getMonth() + 1)) +
    "-" +
    (new Date(timeString).getDate() > 10
      ? new Date(timeString).getDate()
      : "0" + new Date(timeString).getDate())
  );
};
