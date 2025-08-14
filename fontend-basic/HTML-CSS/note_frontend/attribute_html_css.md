### 📘 **Toàn bộ thuộc tính CSS & ý nghĩa tiếng Việt**

#### ⚙️ Nhóm 1: Thuộc tính nền (Background)

| **Thuộc tính CSS**      | **Ý nghĩa tiếng Việt**                                                              |
| ----------------------- | ----------------------------------------------------------------------------------- |
| `background`            | Thuộc tính rút gọn cho tất cả các thuộc tính nền (`background-color`, `image`, ...) |
| `background-attachment` | Xác định hình nền cuộn theo trang hay cố định                                       |
| `background-blend-mode` | Xác định cách hòa trộn giữa các lớp nền                                             |
| `background-clip`       | Xác định vùng mà hình nền sẽ hiển thị trong phần tử                                 |
| `background-color`      | Màu nền của phần tử                                                                 |
| `background-image`      | Đặt hình ảnh nền cho phần tử                                                        |
| `background-origin`     | Xác định vị trí bắt đầu của hình nền (lề, padding, nội dung)                        |
| `background-position`   | Vị trí của hình nền                                                                 |
| `background-repeat`     | Có lặp lại hình nền hay không                                                       |
| `background-size`       | Kích thước của hình nền                                                             |

#### 🧱 Nhóm 2: Layout & hiển thị

| **Thuộc tính CSS**                     | **Ý nghĩa tiếng Việt**                                                |
| -------------------------------------- | --------------------------------------------------------------------- |
| `display`                              | Xác định kiểu hiển thị của phần tử (block, inline, flex, grid...)     |
| `position`                             | Vị trí phần tử (static, relative, absolute, fixed, sticky)            |
| `top`, `right`, `bottom`, `left`       | Định vị phần tử so với vùng chứa                                      |
| `z-index`                              | Xác định thứ tự chồng lớp của phần tử                                 |
| `float`                                | Dùng để đẩy phần tử sang trái/phải                                    |
| `clear`                                | Ngăn phần tử nằm bên cạnh phần tử float                               |
| `overflow`, `overflow-x`, `overflow-y` | Xử lý khi nội dung tràn ra ngoài phần tử                              |
| `box-sizing`                           | Quy định cách tính kích thước phần tử (gồm padding, border hay không) |

#### 📐 Nhóm 3: Kích thước

| **Thuộc tính CSS**         | **Ý nghĩa tiếng Việt**                 |
| -------------------------- | -------------------------------------- |
| `width`, `height`          | Chiều rộng và chiều cao phần tử        |
| `min-width`, `max-width`   | Chiều rộng nhỏ nhất/lớn nhất phần tử   |
| `min-height`, `max-height` | Chiều cao nhỏ nhất/lớn nhất phần tử    |
| `aspect-ratio`             | Tỷ lệ khung hình mong muốn của phần tử |

#### 🎨 Nhóm 4: Màu sắc & viền

| **Thuộc tính CSS**                                           | **Ý nghĩa tiếng Việt**             |
| ------------------------------------------------------------ | ---------------------------------- |
| `color`                                                      | Màu chữ của phần tử                |
| `border`, `border-width`, `border-style`, `border-color`     | Định dạng viền phần tử             |
| `border-radius`                                              | Bo góc phần tử (làm tròn)          |
| `outline`, `outline-color`, `outline-style`, `outline-width` | Định dạng đường viền ngoài phần tử |
| `box-shadow`                                                 | Tạo hiệu ứng đổ bóng cho phần tử   |

#### 🖋️ Nhóm 5: Văn bản & font chữ

| **Thuộc tính CSS** | **Ý nghĩa tiếng Việt**                    |
| ------------------ | ----------------------------------------- |
| `font-family`      | Tên phông chữ sử dụng                     |
| `font-size`        | Kích thước chữ                            |
| `font-style`       | Kiểu chữ nghiêng, bình thường             |
| `font-weight`      | Độ đậm của chữ                            |
| `letter-spacing`   | Khoảng cách giữa các ký tự                |
| `line-height`      | Chiều cao dòng                            |
| `text-align`       | Căn chỉnh văn bản (trái, phải, giữa, đều) |
| `text-decoration`  | Trang trí chữ (gạch dưới, gạch ngang...)  |
| `text-transform`   | Chuyển đổi chữ hoa/thường                 |
| `white-space`      | Quy định cách xử lý khoảng trắng          |
| `word-spacing`     | Khoảng cách giữa các từ                   |

#### 🔄 Nhóm 6: Hiệu ứng & hoạt ảnh

| **Thuộc tính CSS**                                                                                                                                                                                     | **Ý nghĩa tiếng Việt**                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ----------------------------------------------------- |
| `transition`, `transition-delay`, `transition-duration`, `transition-property`, `transition-timing-function`                                                                                           | Thuộc tính chuyển động mượt của CSS                   |
| `animation`, `animation-name`, `animation-duration`, `animation-delay`, `animation-timing-function`, `animation-fill-mode`, `animation-direction`, `animation-iteration-count`, `animation-play-state` | Thuộc tính điều khiển hoạt ảnh CSS                    |
| `transform`                                                                                                                                                                                            | Biến đổi phần tử: xoay, nghiêng, co giãn, dịch chuyển |
| `filter`, `backdrop-filter`                                                                                                                                                                            | Hiệu ứng hình ảnh như làm mờ, đổ bóng                 |

#### 🧭 Nhóm 7: Flexbox & Grid

| **Thuộc tính CSS**                               | **Ý nghĩa tiếng Việt**                           |
| ------------------------------------------------ | ------------------------------------------------ |
| `display: flex`                                  | Bố cục linh hoạt theo chiều ngang hoặc dọc       |
| `flex-direction`                                 | Hướng chính của phần tử con trong flex container |
| `justify-content`                                | Căn chỉnh phần tử con theo trục chính            |
| `align-items`                                    | Căn chỉnh phần tử con theo trục phụ              |
| `align-content`                                  | Căn chỉnh các dòng flex                          |
| `align-self`                                     | Ghi đè `align-items` cho phần tử cụ thể          |
| `flex-grow`, `flex-shrink`, `flex-basis`, `flex` | Kiểm soát tỷ lệ co giãn của phần tử con          |
| `order`                                          | Thay đổi thứ tự sắp xếp phần tử con              |

\| `display: grid`             | Hiển thị phần tử theo hệ thống lưới                                                  |
\| `grid-template-columns`, `grid-template-rows` | Xác định số và kích thước cột/hàng trong lưới                           |
\| `grid-gap`, `row-gap`, `column-gap` | Khoảng cách giữa các hàng/cột                                                |
\| `grid-column`, `grid-row`   | Vị trí hàng, cột của phần tử trong lưới                                              |
\| `place-items`, `place-content`, `place-self` | Gộp các thuộc tính căn chỉnh trong Grid và Flexbox                   |

#### ⚙️ Nhóm 8: Các thuộc tính khác

| **Thuộc tính CSS** | **Ý nghĩa tiếng Việt**                                 |
| ------------------ | ------------------------------------------------------ |
| `cursor`           | Định dạng con trỏ chuột khi rê vào phần tử             |
| `visibility`       | Hiển thị hay ẩn phần tử (vẫn giữ không gian chiếm chỗ) |
| `opacity`          | Độ mờ đục của phần tử                                  |
| `pointer-events`   | Có cho phép tương tác chuột hay không                  |
| `user-select`      | Cho phép chọn văn bản hay không                        |

---

🧭 Để phân loại các thuộc tính CSS trong danh sách bạn cung cấp, tôi sẽ nhóm chúng theo chức năng hoặc mục đích sử dụng trong CSS. Các nhóm chính bao gồm các thuộc tính liên quan đến bố cục, kiểu chữ, hình ảnh, hoạt hình, viền, nền, cuộn, và các thuộc tính khác. Dưới đây là bảng phân loại với mô tả ngắn gọn cho từng nhóm và các thuộc tính tương ứng, giữ nguyên các thuật ngữ đã dịch trong bảng trước.


| **Nhóm** | **Mô tả** | **Thuộc tính CSS** |
|----------|-----------|--------------------|
| **Bố cục (Layout)** | Các thuộc tính kiểm soát vị trí, kích thước, căn chỉnh và cách hiển thị của phần tử | `align-content`, `align-items`, `align-self`, `block-size`, `bottom`, `box-sizing`, `clear`, `column-count`, `column-fill`, `column-gap`, `column-span`, `column-width`, `columns`, `display`, `flex`, `flex-basis`, `flex-direction`, `flex-flow`, `flex-grow`, `flex-shrink`, `flex-wrap`, `float`, `gap`, `grid`, `grid-area`, `grid-auto-columns`, `grid-auto-flow`, `grid-auto-rows`, `grid-column`, `grid-column-end`, `grid-column-start`, `grid-row`, `grid-row-end`, `grid-row-start`, `grid-template`, `grid-template-areas`, `grid-template-columns`, `grid-template-rows`, `height`, `inline-size`, `inset`, `inset-block`, `inset-block-end`, `inset-block-start`, `inset-inline`, `inset-inline-end`, `inset-inline-start`, `justify-content`, `justify-items`, `justify-self`, `left`, `margin`, `margin-block`, `margin-block-end`, `margin-block-start`, `margin-bottom`, `margin-inline`, `margin-inline-end`, `margin-inline-start`, `margin-left`, `margin-right`, `margin-top`, `max-block-size`, `max-height`, `max-inline-size`, `max-width`, `min-block-size`, `min-height`, `min-inline-size`, `min-width`, `order`, `padding`, `padding-block`, `padding-block-end`, `padding-block-start`, `padding-bottom`, `padding-inline`, `padding-inline-end`, `padding-inline-start`, `padding-left`, `padding-right`, `padding-top`, `place-content`, `place-items`, `place-self`, `position`, `right`, `row-gap`, `top`, `width` |
| **Viền (Border)** | Các thuộc tính kiểm soát viền, góc bo tròn và hình ảnh viền | `border`, `border-block`, `border-block-color`, `border-block-end`, `border-block-end-color`, `border-block-end-style`, `border-block-end-width`, `border-block-start`, `border-block-start-color`, `border-block-start-style`, `border-block-start-width`, `border-block-style`, `border-block-width`, `border-bottom`, `border-bottom-color`, `border-bottom-left-radius`, `border-bottom-right-radius`, `border-bottom-style`, `border-bottom-width`, `border-color`, `border-end-end-radius`, `border-end-start-radius`, `border-image`, `border-image-outset`, `border-image-repeat`, `border-image-slice`, `border-image-source`, `border-image-width`, `border-inline`, `border-inline-color`, `border-inline-end`, `border-inline-end-color`, `border-inline-end-style`, `border-inline-end-width`, `border-inline-start`, `border-inline-start-color`, `border-inline-start-style`, `border-inline-start-width`, `border-inline-style`, `border-inline-width`, `border-left`, `border-left-color`, `border-left-style`, `border-left-width`, `border-radius`, `border-right`, `border-right-color`, `border-right-style`, `border-right-width`, `border-start-end-radius`, `border-start-start-radius`, `border-style`, `border-top`, `border-top-color`, `border-top-left-radius`, `border-top-right-radius`, `border-top-style`, `border-top-width`, `border-width`, `outline`, `outline-color`, `outline-offset`, `outline-style`, `outline-width` |
| **Nền (Background)** | Các thuộc tính kiểm soát màu sắc, hình ảnh và hiệu ứng nền | `background`, `background-attachment`, `background-blend-mode`, `background-clip`, `background-color`, `background-image`, `background-origin`, `background-position`, `background-position-x`, `background-position-y`, `background-repeat`, `background-size`, `backdrop-filter` |
| **Kiểu chữ (Typography)** | Các thuộc tính liên quan đến văn bản, phông chữ và định dạng chữ | `color`, `direction`, `font`, `font-family`, `font-feature-settings`, `font-kerning`, `font-language-override`, `@font-face`, `@font-palette-values`, `font-size`, `font-size-adjust`, `font-stretch`, `font-style`, `font-synthesis`, `font-variant`, `font-variant-alternates`, `font-variant-caps`, `font-variant-east-asian`, `font-variant-ligatures`, `font-variant-numeric`, `font-variant-position`, `font-weight`, `hanging-punctuation`, `hyphens`, `hyphenate-character`, `letter-spacing`, `line-height`, `quotes`, `tab-size`, `text-align`, `text-align-last`, `text-combine-upright`, `text-decoration`, `text-decoration-color`, `text-decoration-line`, `text-decoration-style`, `text-decoration-thickness`, `text-emphasis`, `text-emphasis-color`, `text-emphasis-position`, `text-emphasis-style`, `text-indent`, `text-justify`, `text-orientation`, `text-overflow`, `text-shadow`, `text-transform`, `text-underline-offset`, `text-underline-position`, `unicode-bidi`, `white-space`, `word-break`, `word-spacing`, `word-wrap`, `writing-mode` |
| **Hoạt hình (Animation)** | Các thuộc tính kiểm soát hiệu ứng hoạt hình và chuyển đổi | `animation`, `animation-delay`, `animation-direction`, `animation-duration`, `animation-fill-mode`, `animation-iteration-count`, `animation-name`, `animation-play-state`, `animation-timing-function`, `@keyframes`, `offset`, `offset-anchor`, `offset-distance`, `offset-path`, `offset-position`, `offset-rotate`, `rotate`, `scale`, `transform`, `transform-origin`, `transform-style`, `transition`, `transition-delay`, `transition-duration`, `transition-property`, `transition-timing-function`, `translate` |
| **Cuộn (Scroll)** | Các thuộc tính kiểm soát hành vi cuộn và neo | `overscroll-behavior`, `overscroll-behavior-block`, `overscroll-behavior-inline`, `overscroll-behavior-x`, `overscroll-behavior-y`, `scroll-behavior`, `scroll-margin`, `scroll-margin-block`, `scroll-margin-block-end`, `scroll-margin-block-start`, `scroll-margin-bottom`, `scroll-margin-inline`, `scroll-margin-inline-end`, `scroll-margin-inline-start`, `scroll-margin-left`, `scroll-margin-right`, `scroll-margin-top`, `scroll-padding`, `scroll-padding-block`, `scroll-padding-block-end`, `scroll-padding-block-start`, `scroll-padding-bottom`, `scroll-padding-inline`, `scroll-padding-inline-end`, `scroll-padding-inline-start`, `scroll-padding-left`, `scroll-padding-right`, `scroll-padding-top`, `scroll-snap-align`, `scroll-snap-stop`, `scroll-snap-type`, `scrollbar-color` |
| **Hình ảnh và hiệu ứng (Image & Effects)** | Các thuộc tính liên quan đến xử lý hình ảnh, mặt nạ và hiệu ứng đồ họa | `box-shadow`, `clip`, `clip-path`, `filter`, `image-rendering`, `mask`, `mask-clip`, `mask-composite`, `mask-image`, `mask-mode`, `mask-origin`, `mask-position`, `mask-repeat`, `mask-size`, `mask-type`, `mix-blend-mode`, `object-fit`, `object-position`, `opacity` |
| **Bảng (Table)** | Các thuộc tính liên quan đến bố cục bảng | `border-collapse`, `border-spacing`, `caption-side`, `empty-cells`, `table-layout` |
| **Danh sách (List)** | Các thuộc tính liên quan đến định dạng danh sách | `list-style`, `list-style-image`, `list-style-position`, `list-style-type` |
| **In ấn (Print)** | Các thuộc tính kiểm soát ngắt trang và định dạng khi in | `break-after`, `break-before`, `break-inside`, `orphans`, `page-break-after`, `page-break-before`, `page-break-inside`, `widows`, `@page` |
| **SVG và Đồ họa (SVG & Graphics)** | Các thuộc tính liên quan đến đồ họa SVG và vẽ | `marker`, `marker-end`, `marker-mid`, `marker-start`, `paint-order`, `shape-outside` |
| **Tương tác người dùng (User Interaction)** | Các thuộc tính kiểm soát tương tác của người dùng | `accent-color`, `caret-color`, `cursor`, `pointer-events`, `resize`, `user-select` |
| **3D và phối cảnh (3D & Perspective)** | Các thuộc tính liên quan đến không gian 3D | `backface-visibility`, `perspective`, `perspective-origin` |
| **Bộ đếm (Counter)** | Các thuộc tính liên quan đến bộ đếm CSS | `counter-increment`, `counter-reset`, `counter-set`, `@counter-style` |
| **Quản lý kiểu (Style Management)** | Các thuộc tính hoặc quy tắc liên quan đến quản lý và điều kiện kiểu | `@charset`, `@container`, `@import`, `@layer`, `@namespace`, `@property`, `@scope`, `@starting-style`, `@supports` |
| **Khác (Miscellaneous)** | Các thuộc tính không thuộc nhóm cụ thể | `aspect-ratio`, `box-decoration-break`, `box-reflect`, `color-scheme`, `isolation`, `line-break`, `overflow`, `overflow-anchor`, `overflow-wrap`, `overflow-x`, `overflow-y`, `vertical-align`, `visibility`, `z-index`, `zoom` |

### Giải thích các nhóm
1. **Bố cục (Layout)**: Bao gồm các thuộc tính liên quan đến cách phần tử được định vị, căn chỉnh, hoặc thay đổi kích thước trong bố cục trang.
2. **Viền (Border)**: Liên quan đến việc định dạng viền, bao gồm màu sắc, kiểu, độ rộng và các góc bo tròn.
3. **Nền (Background)**: Các thuộc tính kiểm soát nền của phần tử, như màu sắc, hình ảnh, và các hiệu ứng liên quan.
4. **Kiểu chữ (Typography)**: Liên quan đến định dạng văn bản, phông chữ, khoảng cách, và các hiệu ứng văn bản khác.
5. **Hoạt hình (Animation)**: Bao gồm các thuộc tính kiểm soát hoạt hình, chuyển đổi, và các hiệu ứng động.
6. **Cuộn (Scroll)**: Các thuộc tính quản lý hành vi cuộn, neo khi cuộn, và định dạng thanh cuộn.
7. **Hình ảnh và hiệu ứng (Image & Effects)**: Liên quan đến xử lý hình ảnh, mặt nạ, và các hiệu ứng đồ họa như bóng hoặc độ mờ.
8. **Bảng (Table)**: Các thuộc tính định dạng bố cục và hiển thị của bảng.
9. **Danh sách (List)**: Các thuộc tính kiểm soát cách hiển thị danh sách và dấu đầu dòng.
10. **In ấn (Print)**: Các thuộc tính quản lý ngắt trang và định dạng khi in tài liệu.
11. **SVG và Đồ họa (SVG & Graphics)**: Các thuộc tính liên quan đến đồ họa SVG, như dấu và hình dạng.
12. **Tương tác người dùng (User Interaction)**: Các thuộc tính kiểm soát cách người dùng tương tác với phần tử, như con trỏ hoặc khả năng chọn văn bản.
13. **3D và phối cảnh (3D & Perspective)**: Các thuộc tính tạo hiệu ứng 3D hoặc phối cảnh cho phần tử.
14. **Bộ đếm (Counter)**: Các thuộc tính quản lý bộ đếm CSS để đánh số hoặc theo dõi.
15. **Quản lý kiểu (Style Management)**: Các quy tắc hoặc thuộc tính liên quan đến cách quản lý hoặc áp dụng kiểu CSS.
16. **Khác (Miscellaneous)**: Các thuộc tính không thuộc nhóm cụ thể nào, như tỷ lệ khung hình hoặc thu phóng.

---
🧭 Dưới đây là bảng dịch tất cả các thuộc tính CSS được liệt kê, với tên thuộc tính và mô tả đã được dịch sang tiếng Việt. Bảng được sắp xếp theo thứ tự chữ cái để dễ theo dõi.

| **Thuộc tính CSS** | **Mô tả (Tiếng Việt)** |
|---------------------|------------------------|
| **accent-color** | Chỉ định màu nhấn cho các điều khiển giao diện người dùng |
| **align-content** | Chỉ định cách căn chỉnh các dòng bên trong một container linh hoạt khi các mục không sử dụng hết không gian có sẵn |
| **align-items** | Chỉ định cách căn chỉnh các mục bên trong một container linh hoạt |
| **align-self** | Chỉ định cách căn chỉnh cho các mục được chọn bên trong một container linh hoạt |
| **all** | Đặt lại tất cả các thuộc tính (trừ `unicode-bidi` và `direction`) |
| **animation** | Thuộc tính rút gọn cho tất cả các thuộc tính `animation-*` |
| **animation-delay** | Chỉ định độ trễ cho việc bắt đầu của một hoạt hình |
| **animation-direction** | Chỉ định liệu một hoạt hình nên được phát tiến, lùi hay theo chu kỳ xen kẽ |
| **animation-duration** | Chỉ định thời gian một hoạt hình cần để hoàn thành một chu kỳ |
| **animation-fill-mode** | Chỉ định kiểu dáng cho phần tử khi hoạt hình không phát (trước khi bắt đầu, sau khi kết thúc, hoặc cả hai) |
| **animation-iteration-count** | Chỉ định số lần một hoạt hình nên được phát |
| **animation-name** | Chỉ định tên cho hoạt hình `@keyframes` |
| **animation-play-state** | Chỉ định liệu hoạt hình đang chạy hay bị tạm dừng |
| **animation-timing-function** | Chỉ định đường cong tốc độ của hoạt hình |
| **aspect-ratio** | Chỉ định tỷ lệ khung hình ưu tiên của một phần tử |
| **backdrop-filter** | Xác định hiệu ứng đồ họa cho khu vực phía sau một phần tử |
| **backface-visibility** | Xác định liệu mặt sau của một phần tử có hiển thị khi đối diện với người dùng hay không |
| **background** | Thuộc tính rút gọn cho tất cả các thuộc tính `background-*` |
| **background-attachment** | Cài đặt liệu hình nền có cuộn cùng với phần còn lại của trang hay được cố định |
| **background-blend-mode** | Chỉ định chế độ hòa trộn của mỗi lớp nền (màu/hình ảnh) |
| **background-clip** | Xác định phạm vi mở rộng của nền (màu hoặc hình ảnh) trong một phần tử |
| **background-color** | Chỉ định màu nền của một phần tử |
| **background-image** | Chỉ định một hoặc nhiều hình ảnh nền cho một phần tử |
| **background-origin** | Chỉ định vị trí gốc của hình ảnh nền |
| **background-position** | Chỉ định vị trí của hình ảnh nền |
| **background-position-x** | Chỉ định vị trí của hình ảnh nền trên trục x |
| **background-position-y** | Chỉ định vị trí của hình ảnh nền trên trục y |
| **background-repeat** | Cài đặt cách/liệu một hình ảnh nền sẽ được lặp lại |
| **background-size** | Chỉ định kích thước của các hình ảnh nền |
| **block-size** | Chỉ định kích thước của một phần tử theo hướng khối |
| **border** | Thuộc tính rút gọn cho `border-width`, `border-style` và `border-color` |
| **border-block** | Thuộc tính rút gọn cho `border-block-width`, `border-block-style` và `border-block-color` |
| **border-block-color** | Cài đặt màu của các đường viền ở đầu và cuối theo hướng khối |
| **border-block-end** | Thuộc tính rút gọn cho `border-block-end-width`, `border-block-end-style` và `border-block-end-color` |
| **border-block-end-color** | Cài đặt màu của đường viền ở cuối theo hướng khối |
| **border-block-end-style** | Cài đặt kiểu của đường viền ở cuối theo hướng khối |
| **border-block-end-width** | Cài đặt độ rộng của đường viền ở cuối theo hướng khối |
| **border-block-start** | Thuộc tính rút gọn cho `border-block-start-width`, `border-block-start-style` và `border-block-start-color` |
| **border-block-start-color** | Cài đặt màu của đường viền ở đầu theo hướng khối |
| **border-block-start-style** | Cài đặt kiểu của đường viền ở đầu theo hướng khối |
| **border-block-start-width** | Cài đặt độ rộng của đường viền ở đầu theo hướng khối |
| **border-block-style** | Cài đặt kiểu của các đường viền ở đầu và cuối theo hướng khối |
| **border-block-width** | Cài đặt độ rộng của các đường viền ở đầu và cuối theo hướng khối |
| **border-bottom** | Thuộc tính rút gọn cho `border-bottom-width`, `border-bottom-style` và `border-bottom-color` |
| **border-bottom-color** | Cài đặt màu của đường viền dưới |
| **border-bottom-left-radius** | Xác định bán kính của đường viền ở góc dưới bên trái |
| **border-bottom-right-radius** | Xác định bán kính của đường viền ở góc dưới bên phải |
| **border-bottom-style** | Cài đặt kiểu của đường viền dưới |
| **border-bottom-width** | Cài đặt độ rộng của đường viền dưới |
| **border-collapse** | Cài đặt liệu các đường viền của bảng nên gộp thành một đường viền duy nhất hay tách biệt |
| **border-color** | Cài đặt màu của bốn đường viền |
| **border-end-end-radius** | Cài đặt bán kính của góc giữa phía cuối khối và phía cuối nội tuyến của phần tử |
| **border-end-start-radius** | Cài đặt bán kính của góc giữa phía cuối khối và phía bắt đầu nội tuyến của phần tử |
| **border-image** | Thuộc tính rút gọn cho tất cả các thuộc tính `border-image-*` |
| **border-image-outset** | Chỉ định khoảng cách mà khu vực hình ảnh viền mở rộng ra ngoài hộp viền |
| **border-image-repeat** | Chỉ định liệu hình ảnh viền nên được lặp lại, làm tròn hay kéo dãn |
| **border-image-slice** | Chỉ định cách cắt hình ảnh viền |
| **border-image-source** | Chỉ định đường dẫn đến hình ảnh được sử dụng làm viền |
| **border-image-width** | Chỉ định độ rộng của hình ảnh viền |
| **border-inline** | Thuộc tính rút gọn cho `border-inline-width`, `border-inline-style` và `border-inline-color` |
| **border-inline-color** | Cài đặt màu của các đường viền ở đầu và cuối theo hướng nội tuyến |
| **border-inline-end** | Thuộc tính rút gọn cho `border-inline-end-width`, `border-inline-end-style` và `border-inline-end-color` |
| **border-inline-end-color** | Cài đặt màu của đường viền ở cuối theo hướng nội tuyến |
| **border-inline-end-style** | Cài đặt kiểu của đường viền ở cuối theo hướng nội tuyến |
| **border-inline-end-width** | Cài đặt độ rộng của đường viền ở cuối theo hướng nội tuyến |
| **border-inline-start** | Thuộc tính rút gọn cho `border-inline-start-width`, `border-inline-start-style` và `border-inline-start-color` |
| **border-inline-start-color** | Cài đặt màu của đường viền ở đầu theo hướng nội tuyến |
| **border-inline-start-style** | Cài đặt kiểu của đường viền ở đầu theo hướng nội tuyến |
| **border-inline-start-width** | Cài đặt độ rộng của đường viền ở đầu theo hướng nội tuyến |
| **border-inline-style** | Cài đặt kiểu của các đường viền ở đầu và cuối theo hướng nội tuyến |
| **border-inline-width** | Cài đặt độ rộng của các đường viền ở đầu và cuối theo hướng nội tuyến |
| **border-left** | Thuộc tính rút gọn cho tất cả các thuộc tính `border-left-*` |
| **border-left-color** | Cài đặt màu của đường viền trái |
| **border-left-style** | Cài đặt kiểu của đường viền trái |
| **border-left-width** | Cài đặt độ rộng của đường viền trái |
| **border-radius** | Thuộc tính rút gọn cho bốn thuộc tính `border-*-radius` |
| **border-right** | Thuộc tính rút gọn cho tất cả các thuộc tính `border-right-*` |
| **border-right-color** | Cài đặt màu của đường viền phải |
| **border-right-style** | Cài đặt kiểu của đường viền phải |
| **border-right-width** | Cài đặt độ rộng của đường viền phải |
| **border-spacing** | Cài đặt khoảng cách giữa các đường viền của các ô liền kề |
| **border-start-end-radius** | Cài đặt bán kính của góc giữa phía bắt đầu khối và phía cuối nội tuyến của phần tử |
| **border-start-start-radius** | Cài đặt bán kính của góc giữa phía bắt đầu khối và phía bắt đầu nội tuyến của phần tử |
| **border-style** | Cài đặt kiểu của bốn đường viền |
| **border-top** | Thuộc tính rút gọn cho `border-top-width`, `border-top-style` và `border-top-color` |
| **border-top-color** | Cài đặt màu của đường viền trên |
| **border-top-left-radius** | Xác định bán kính của đường viền ở góc trên bên trái |
| **border-top-right-radius** | Xác định bán kính của đường viền ở góc trên bên phải |
| **border-top-style** | Cài đặt kiểu của đường viền trên |
| **border-top-width** | Cài đặt độ rộng của đường viền trên |
| **border-width** | Cài đặt độ rộng của bốn đường viền |
| **bottom** | Cài đặt vị trí phần tử từ phía dưới của phần tử cha |
| **box-decoration-break** | Cài đặt hành vi của nền và viền của một phần tử tại điểm ngắt trang, hoặc với các phần tử nội tuyến, tại điểm ngắt dòng |
| **box-reflect** | Thu Comunicación: **box-reflect** được sử dụng để tạo phản chiếu của một phần tử |
| **box-shadow** | Gắn một hoặc nhiều bóng cho một phần tử |
| **box-sizing** | Xác định cách tính toán chiều rộng và chiều cao của một phần tử: có bao gồm phần đệm và viền hay không |
| **break-after** | Chỉ định liệu ngắt trang, cột hoặc vùng có nên xảy ra sau phần tử được chỉ định hay không |
| **break-before** | Chỉ định liệu ngắt trang, cột hoặc vùng có nên xảy ra trước phần tử được chỉ định hay không |
| **break-inside** | Chỉ định liệu ngắt trang, cột hoặc vùng có nên xảy ra bên trong phần tử được chỉ định hay không |
| **caption-side** | Chỉ định vị trí đặt chú thích của bảng |
| **caret-color** | Chỉ định màu của con trỏ (caret) trong các ô nhập, khu vực văn bản hoặc bất kỳ phần tử nào có thể chỉnh sửa |
| **@charset** | Chỉ định mã hóa ký tự được sử dụng trong bảng định kiểu |
| **clear** | Chỉ định điều gì sẽ xảy ra với phần tử đứng cạnh một phần tử nổi |
| **clip** | Không còn được sử dụng, thay bằng `clip-path`. Cắt một phần tử được định vị tuyệt đối |
| **clip-path** | Cắt một phần tử thành một hình dạng cơ bản hoặc theo nguồn SVG |
| **color** | Cài đặt màu của văn bản |
| **color-scheme** | Chỉ định giao diện màu của hệ điều hành mà một phần tử nên hiển thị |
| **column-count** | Chỉ định số cột mà một phần tử nên được chia thành |
| **column-fill** | Chỉ định cách lấp đầy các cột, cân bằng hay không |
| **column-gap** | Chỉ định khoảng cách giữa các cột |
| **column-rule** | Thuộc tính rút gọn cho tất cả các thuộc tính `column-rule-*` |
| **column-rule-color** | Chỉ định màu của đường kẻ giữa các cột |
| **column-rule-style** | Chỉ định kiểu của đường kẻ giữa các cột |
| **column-rule-width** | Chỉ định độ rộng của đường kẻ giữa các cột |
| **column-span** | Chỉ định một phần tử nên trải dài qua bao nhiêu cột |
| **column-width** | Chỉ định chiều rộng cột |
| **columns** | Thuộc tính rút gọn cho `column-width` và `column-count` |
| **@container** | Xác định kiểu dáng cho các phần tử trong container, phụ thuộc vào kích thước hoặc kiểu của container |
| **content** | Sử dụng với các phần tử giả `:before` và `:after` để chèn nội dung được tạo ra |
| **counter-increment** | Tăng hoặc giảm giá trị của một hoặc nhiều bộ đếm CSS |
| **counter-reset** | Tạo hoặc đặt lại một hoặc nhiều bộ đếm CSS |
| **counter-set** | Tạo hoặc cài đặt một hoặc nhiều bộ đếm CSS |
| **@counter-style** | Cho phép bạn xác định kiểu bộ đếm tùy chỉnh |
| **cursor** | Chỉ định con trỏ chuột được hiển thị khi di chuột qua một phần tử |
| **direction** | Chỉ định hướng văn bản/hướng viết |
| **display** | Chỉ định cách một phần tử HTML nhất định nên được hiển thị |
| **empty-cells** | Chỉ định liệu có hiển thị viền và nền trên các ô trống trong bảng hay không |
| **filter** | Xác định các hiệu ứng (ví dụ: làm mờ hoặc thay đổi màu) trên một phần tử trước khi hiển thị |
| **flex** | Thuộc tính rút gọn cho `flex-grow`, `flex-shrink` và `flex-basis` |
| **flex-basis** | Chỉ định chiều dài ban đầu của một mục linh hoạt |
| **flex-direction** | Chỉ định hướng của các mục linh hoạt |
| **flex-flow** | Thuộc tính rút gọn cho `flex-direction` và `flex-wrap` |
| **flex-grow** | Chỉ định mức độ một mục sẽ phát triển so với các mục khác |
| **flex-shrink** | Chỉ định mức độ một mục sẽ co lại so với các mục khác |
| **flex-wrap** | Chỉ định liệu các mục linh hoạt có nên bọc lại hay không |
| **float** | Chỉ định liệu một phần tử nên nổi sang trái, phải hay không nổi |
| **font** | Thuộc tính rút gọn cho `font-style`, `font-variant`, `font-weight`, `font-size/line-height` và `font-family` |
| **@font-face** | Chỉ định phông chữ tùy chỉnh để sử dụng hiển thị văn bản |
| **font-family** | Chỉ định họ phông chữ cho văn bản |
| **font-feature-settings** | Cho phép kiểm soát các tính năng typographic nâng cao trong phông chữ OpenType |
| **font-kerning** | Kiểm soát việc sử dụng thông tin kerning (khoảng cách giữa các chữ cái) |
| **font-language-override** | Kiểm soát việc sử dụng các ký tự dành riêng cho ngôn ngữ trong phông chữ |
| **@font-palette-values** | Cho phép tùy chỉnh các giá trị mặc định của bảng màu phông chữ |
| **font-size** | Chỉ định kích thước phông chữ của văn bản |
| **font-size-adjust** | Giữ khả năng đọc và kích thước của văn bản khi sử dụng phông chữ dự phòng |
| **font-stretch** | Chọn một mặt chữ bình thường, thu gọn hoặc mở rộng từ một họ phông chữ |
| **font-style** | Chỉ định kiểu phông chữ cho văn bản |
| **font-synthesis** | Kiểm soát phông chữ bị thiếu (đậm hoặc nghiêng) có thể được tổng hợp bởi trình duyệt |
| **font-variant** | Chỉ định liệu văn bản có nên được hiển thị bằng phông chữ chữ nhỏ hay không |
| **font-variant-alternates** | Kiểm soát việc sử dụng các ký tự thay thế liên quan đến các tên thay thế được xác định trong `@font-feature-values` |
| **font-variant-caps** | Kiểm soát việc sử dụng các ký tự thay thế cho chữ cái in hoa |
| **font-variant-east-asian** | Kiểm soát việc sử dụng các ký tự thay thế cho các kịch bản Đông Á (ví dụ: Nhật Bản và Trung Quốc) |
| **font-variant-ligatures** | Kiểm soát các chữ ghép và dạng ngữ cảnh được sử dụng trong nội dung văn bản của phần tử |
| **font-variant-numeric** | Kiểm soát việc sử dụng các ký tự thay thế cho số, phân số và dấu thứ tự |
| **font-variant-position** | Kiểm soát việc sử dụng các ký tự thay thế có kích thước nhỏ hơn được định vị như chỉ số trên hoặc dưới so với đường cơ sở của phông chữ |
| **font-weight** | Chỉ định độ đậm của phông chữ |
| **gap** | Thuộc tính rút gọn cho `row-gap` và `column-gap` |
| **grid** | Thuộc tính rút gọn cho `grid-template-rows`, `grid-template-columns`, `grid-template-areas`, `grid-auto-rows`, `grid-auto-columns` và `grid-auto-flow` |
| **grid-area** | Chỉ định tên cho mục lưới hoặc là thuộc tính rút gọn cho `grid-row-start`, `grid-column-start`, `grid-row-end` và `grid-column-end` |
| **grid-auto-columns** | Chỉ định kích thước cột mặc định |
| **grid-auto-flow** | Chỉ định cách các mục tự động được chèn vào lưới |
| **grid-auto-rows** | Chỉ định kích thước hàng mặc định |
| **grid-column** | Thuộc tính rút gọn cho `grid-column-start` và `grid-column-end` |
| **grid-column-end** | Chỉ định nơi kết thúc mục lưới |
| **grid-column-start** | Chỉ định nơi bắt đầu mục lưới |
| **grid-row** | Thuộc tính rút gọn cho `grid-row-start` và `grid-row-end` |
| **grid-row-end** | Chỉ định nơi kết thúc mục lưới |
| **grid-row-start** | Chỉ định nơi bắt đầu mục lưới |
| **grid-template** | Thuộc tính rút gọn cho `grid-template-rows`, `grid-template-columns` và `grid-template-areas` |
| **grid-template-areas** | Chỉ định cách hiển thị các cột và hàng, sử dụng các mục lưới được đặt tên |
| **grid-template-columns** | Chỉ định kích thước của các cột và số lượng cột trong bố cục lưới |
| **grid-template-rows** | Chỉ định kích thước của các hàng trong bố cục lưới |
| **hanging-punctuation** | Chỉ định liệu một ký tự dấu câu có thể được đặt ngoài hộp dòng hay không |
| **height** | Cài đặt chiều cao của một phần tử |
| **hyphens** | Cài đặt cách chia nhỏ các từ để cải thiện bố cục văn bản |
| **hyphenate-character** | Cài đặt ký tự được sử dụng ở cuối dòng, trước khi ngắt gạch nối |
| **image-rendering** | Chỉ định loại thuật toán sử dụng để thay đổi kích thước hình ảnh |
| **@import** | Cho phép nhập một bảng định kiểu vào một bảng định kiểu khác |
| **initial-letter** | Chỉ định kích thước của chữ cái đầu tiên và tùy chọn số dòng mà chữ cái đầu tiên nên chìm xuống trong văn bản |
| **inline-size** | Chỉ định kích thước của một phần tử theo hướng nội tuyến |
| **inset** | Chỉ định khoảng cách giữa một phần tử và phần tử cha |
| **inset-block** | Chỉ định khoảng cách giữa một phần tử và phần tử cha theo hướng khối |
| **inset-block-end** | Chỉ định khoảng cách giữa cuối phần tử và phần tử cha theo hướng khối |
| **inset-block-start** | Chỉ định khoảng cách giữa đầu phần tử và phần tử cha theo hướng khối |
| **inset-inline** | Chỉ định khoảng cách giữa một phần tử và phần tử cha theo hướng nội tuyến |
| **inset-inline-end** | Chỉ định khoảng cách giữa cuối phần tử và phần tử cha theo hướng nội tuyến |
| **inset-inline-start** | Chỉ định khoảng cách giữa đầu phần tử và phần tử cha theo hướng nội tuyến |
| **isolation** | Xác định liệu một phần tử có phải tạo một nội dung xếp chồng mới hay không |
| **justify-content** | Chỉ định cách căn chỉnh các mục bên trong một container linh hoạt khi các mục không sử dụng hết không gian có sẵn |
| **justify-items** | Được cài trên container lưới. Chỉ định cách căn chỉnh các mục lưới theo hướng nội tuyến |
| **justify-self** | Được cài trên mục lưới. Chỉ định cách căn chỉnh mục lưới theo hướng nội tuyến |
| **@keyframes** | Kiểm soát các bước trong một hoạt hình bằng cách xác định kiểu dáng cho các điểm dọc theo chuỗi hoạt hình |
| **@layer** | Kiểm soát cách các lớp tầng CSS đánh giá thứ tự của các kiểu |
| **left** | Chỉ định vị trí bên trái của một phần tử được định vị |
| **letter-spacing** | Tăng hoặc giảm khoảng cách giữa các ký tự trong văn bản |
| **line-break** | Chỉ định cách/nghĩa là có nên ngắt dòng hay không |
| **line-height** | Cài đặt chiều cao dòng |
| **list-style** | Cài đặt tất cả các thuộc tính cho danh sách trong một khai báo |
| **list-style-image** | Chỉ định hình ảnh làm dấu đầu dòng danh sách |
| **list-style-position** | Chỉ định vị trí của các dấu đầu dòng danh sách |
| **list-style-type** | Chỉ định loại dấu đầu dòng danh sách |
| **margin** | Cài đặt tất cả các thuộc tính lề trong một khai báo |
| **margin-block** | Chỉ định lề theo hướng khối |
| **margin-block-end** | Chỉ định lề ở cuối theo hướng khối |
| **margin-block-start** | Chỉ định lề ở đầu theo hướng khối |
| **margin-bottom** | Cài đặt lề dưới của một phần tử |
| **margin-inline** | Chỉ định lề theo hướng nội tuyến |
| **margin-inline-end** | Chỉ định lề ở cuối theo hướng nội tuyến |
| **margin-inline-start** | Chỉ định lề ở đầu theo hướng nội tuyến |
| **margin-left** | Cài đặt lề trái của một phần tử |
| **margin-right** | Cài đặt lề phải của một phần tử |
| **margin-top** | Cài đặt lề trên của một phần tử |
| **marker** | Chỉ định một dấu được vẽ trên tất cả các đỉnh của đường dẫn của một phần tử (đầu tiên, giữa và cuối) |
| **marker-end** | Chỉ định một dấu được vẽ trên đỉnh cuối của đường dẫn của một phần tử |
| **marker-mid** | Chỉ định một dấu được vẽ trên tất cả các đỉnh giữa của đường dẫn của một phần tử |
| **marker-start** | Chỉ định một dấu được vẽ trên đỉnh đầu tiên của đường dẫn của một phần tử |
| **mask** | Thuộc tính rút gọn cho `mask-image`, `mask-mode`, `mask-repeat`, `mask-position`, `mask-clip`, `mask-origin`, `mask-size` và `mask-composite` |
| **mask-clip** | Chỉ định khu vực nào bị ảnh hưởng bởi hình ảnh mặt nạ |
| **mask-composite** | Chỉ định thao tác tổng hợp được sử dụng trên lớp mặt nạ hiện tại với các lớp mặt nạ bên dưới |
| **mask-image** | Chỉ định hình ảnh được sử dụng làm lớp mặt nạ cho một phần tử |
| **mask-mode** | Chỉ định liệu hình ảnh lớp mặt nạ được xử lý như mặt nạ độ sáng hay mặt nạ alpha |
| **mask-origin** | Chỉ định vị trí gốc (khu vực vị trí mặt nạ) của hình ảnh lớp mặt nạ |
| **mask-position** | Cài đặt vị trí bắt đầu của hình ảnh lớp mặt nạ (so với khu vực vị trí mặt nạ) |
| **mask-repeat** | Chỉ định cách hình ảnh lớp mặt nạ được lặp lại |
| **mask-size** | Chỉ định kích thước của hình ảnh lớp mặt nạ |
| **mask-type** | Chỉ định liệu một phần tử `<mask>` SVG được xử lý như mặt nạ độ sáng hay mặt nạ alpha |
| **max-height** | Cài đặt chiều cao tối đa của một phần tử |
| **max-width** | Cài đặt chiều rộng tối đa của một phần tử |
| **@media** | Cài đặt các quy tắc kiểu cho các loại phương tiện/thiết bị/kích thước khác nhau |
| **max-block-size** | Cài đặt kích thước tối đa của một phần tử theo hướng khối |
| **max-inline-size** | Cài đặt kích thước tối đa của một phần tử theo hướng nội tuyến |
| **min-block-size** | Cài đặt kích thước tối thiểu của một phần tử theo hướng khối |
| **min-inline-size** | Cài đặt kích thước tối thiểu của một phần tử theo hướng nội tuyến |
| **min-height** | Cài đặt chiều cao tối thiểu của một phần tử |
| **min-width** | Cài đặt chiều rộng tối thiểu của một phần tử |
| **mix-blend-mode** | Chỉ định cách nội dung của một phần tử nên hòa trộn với nền trực tiếp của nó |
| **@namespace** | Xác định không gian tên XML được sử dụng trong bảng định kiểu |
| **object-fit** | Chỉ định cách nội dung của một phần tử thay thế nên được điều chỉnh để phù hợp với hộp được xác lập bởi chiều cao và chiều rộng được sử dụng |
| **object-position** | Chỉ định cách căn chỉnh của phần tử thay thế bên trong hộp của nó |
| **offset** | Thuộc tính rút gọn cho `offset-anchor`, `offset-distance`, `offset-path`, `offset-position` và `offset-rotate` |
| **offset-anchor** | Chỉ định một điểm trên một phần tử được cố định vào đường dẫn mà nó được hoạt hình |
| **offset-distance** | Chỉ định vị trí dọc theo một đường dẫn nơi một phần tử được hoạt hình được đặt |
| **offset-path** | Chỉ định đường dẫn mà một phần tử được hoạt hình dọc theo |
| **offset-position** | Chỉ định vị trí ban đầu của một phần tử dọc theo một đường dẫn |
| **offset-rotate** | Chỉ định xoay của một phần tử khi nó được hoạt hình dọc theo một đường dẫn |
| **opacity** | Cài đặt mức độ trong suốt cho một phần tử |
| **order** | Cài đặt thứ tự của mục linh hoạt, so với các mục khác |
| **orphans** | Cài đặt số dòng tối thiểu phải được để lại ở dưới cùng của một trang hoặc cột |
| **outline** | Thuộc tính rút gọn cho `outline-width`, `outline-style` và `outline-color` |
| **outline-color** | Cài đặt màu của đường viền ngoài |
| **outline-offset** | Dịch chuyển một đường viền ngoài và vẽ nó ngoài cạnh viền |
| **outline-style** | Cài đặt kiểu của đường viền ngoài |
| **outline-width** | Cài đặt độ rộng của đường viền ngoài |
| **overflow** | Chỉ định điều gì xảy ra nếu nội dung tràn ra ngoài hộp của một phần tử |
| **overflow-anchor** | Chỉ định liệu nội dung trong khu vực có thể xem được trong một container có thể cuộn có nên được đẩy xuống khi nội dung mới được tải phía trên hay không |
| **overflow-wrap** | Chỉ định liệu trình duyệt có thể ngắt dòng với các từ dài nếu chúng tràn ra ngoài container hay không |
| **overflow-x** | Chỉ định liệu có cắt các cạnh trái/phải của nội dung nếu nó tràn ra ngoài khu vực nội dung của phần tử hay không |
| **overflow-y** | Chỉ định liệu có cắt các cạnh trên/dưới của nội dung nếu nó tràn ra ngoài khu vực nội dung của phần tử hay không |
| **overscroll-behavior** | Chỉ định liệu có liên kết cuộn hoặc cung cấp khả năng cuộn quá mức theo hướng x và y |
| **overscroll-behavior-block** | Chỉ định liệu có liên kết cuộn hoặc cung cấp khả năng cuộn quá mức theo hướng khối |
| **overscroll-behavior-inline** | Chỉ định liệu có liên kết cuộn hoặc cung cấp khả năng cuộn quá mức theo hướng nội tuyến |
| **overscroll-behavior-x** | Chỉ định liệu có liên kết cuộn hoặc cung cấp khả năng cuộn quá mức theo hướng x |
| **overscroll-behavior-y** | Chỉ định liệu có liên kết cuộn hoặc cung cấp khả năng cuộn quá mức theo hướng y |
| **padding** | Thuộc tính rút gọn cho tất cả các thuộc tính `padding-*` |
| **padding-block** | Chỉ định phần đệm theo hướng khối |
| **padding-block-end** | Chỉ định phần đệm ở cuối theo hướng khối |
| **padding-block-start** | Chỉ định phần đệm ở đầu theo hướng khối |
| **padding-bottom** | Cài đặt phần đệm dưới của một phần tử |
| **padding-inline** | Chỉ định phần đệm theo hướng nội tuyến |
| **padding-inline-end** | Chỉ định phần đệm ở cuối theo hướng nội tuyến |
| **padding-inline-start** | Chỉ định phần đệm ở đầu theo hướng nội tuyến |
| **padding-left** | Cài đặt phần đệm trái của một phần tử |
| **padding-right** | Cài đặt phần đệm phải của một phần tử |
| **padding-top** | Cài đặt phần đệm trên của một phần tử |
| **@page** | Tùy chỉnh kích thước, hướng và lề của các trang in |
| **page-break-after** | Cài đặt hành vi ngắt trang sau một phần tử. Được thay thế bởi thuộc tính `break-after` |
| **page-break-before** | Cài đặt hành vi ngắt trang trước một phần tử. Được thay thế bởi thuộc tính `break-before` |
| **page-break-inside** | Cài đặt hành vi ngắt trang bên trong một phần tử. Được thay thế bởi thuộc tính `break-inside` |
| **paint-order** | Cài đặt thứ tự vẽ của một phần tử SVG hoặc văn bản |
| **perspective** | Tạo phối cảnh cho một phần tử được định vị 3D |
| **perspective-origin** | Xác định vị trí mà người dùng đang nhìn vào phần tử được định vị 3D |
| **place-content** | Chỉ định giá trị `align-content` và `justify-content` cho bố cục flexbox và lưới |
| **place-items** | Chỉ định giá trị `align-items` và `justify-items` cho bố cục lưới |
| **place-self** | Chỉ định giá trị `align-self` và `justify-self` cho bố cục lưới |
| **pointer-events** | Xác định liệu một phần tử có phản ứng với các sự kiện con trỏ hay không |
| **position** | Chỉ định loại phương pháp định vị được sử dụng cho một phần tử (tĩnh, tương đối, tuyệt đối hoặc cố định) |
| **@property** | Xác định các thuộc tính CSS tùy chỉnh trực tiếp trong bảng định kiểu mà không cần chạy JavaScript |
| **quotes** | Cài đặt loại dấu ngoặc kép cho các câu trích dẫn nhúng |
| **resize** | Xác định liệu (và cách nào) một phần tử có thể thay đổi kích thước bởi người dùng |
| **right** | Chỉ định vị trí bên phải của một phần tử được định vị |
| **rotate** | Chỉ định xoay của một phần tử |
| **row-gap** | Chỉ định khoảng cách giữa các hàng lưới |
| **scale** | Chỉ định kích thước của một phần tử bằng cách phóng to hoặc thu nhỏ |
| **@scope** | Cho phép chọn các phần tử trong các cây con DOM cụ thể và nhắm mục tiêu chính xác mà không cần viết các bộ chọn quá cụ thể |
| **scroll-behavior** | Chỉ định liệu có nên cuộn mượt mà trong một hộp có thể cuộn, thay vì nhảy thẳng |
| **scroll-margin** | Chỉ định lề giữa vị trí neo và container |
| **scroll-margin-block** | Chỉ định lề giữa vị trí neo và container theo hướng khối |
| **scroll-margin-block-end** | Chỉ định lề cuối giữa vị trí neo và container theo hướng khối |
| **scroll-margin-block-start** | Chỉ định lề đầu giữa vị trí neo và container theo hướng khối |
| **scroll-margin-bottom** | Chỉ định lề giữa vị trí neo ở phía dưới và container |
| **scroll-margin-inline** | Chỉ định lề giữa vị trí neo và container theo hướng nội tuyến |
| **scroll-margin-inline-end** | Chỉ định lề cuối giữa vị trí neo và container theo hướng nội tuyến |
| **scroll-margin-inline-start** | Chỉ định lề đầu giữa vị trí neo và container theo hướng nội tuyến |
| **scroll-margin-left** | Chỉ định lề giữa vị trí neo ở phía trái và container |
| **scroll-margin-right** | Chỉ định lề giữa vị trí neo ở phía phải và container |
| **scroll-margin-top** | Chỉ định lề giữa vị trí neo ở phía trên và container |
| **scroll-padding** | Chỉ định khoảng cách từ container đến vị trí neo trên các phần tử con |
| **scroll-padding-block** | Chỉ định khoảng cách theo hướng khối từ container đến vị trí neo trên các phần tử con |
| **scroll-padding-block-end** | Chỉ định khoảng cách theo hướng khối từ cuối container đến vị trí neo trên các phần tử con |
| **scroll-padding-block-start** | Chỉ định khoảng cách theo hướng khối từ đầu container đến vị trí neo trên các phần tử con |
| **scroll-padding-bottom** | Chỉ định khoảng cách từ phía dưới container đến vị trí neo trên các phần tử con |
| **scroll-padding-inline** | Chỉ định khoảng cách theo hướng nội tuyến từ container đến vị trí neo trên các phần tử con |
| **scroll-padding-inline-end** | Chỉ định khoảng cách theo hướng nội tuyến từ cuối container đến vị trí neo trên các phần tử con |
| **scroll-padding-inline-start** | Chỉ định khoảng cách theo hướng nội tuyến từ đầu container đến vị trí neo trên các phần tử con |
| **scroll-padding-left** | Chỉ định khoảng cách từ phía trái container đến vị trí neo trên các phần tử con |
| **scroll-padding-right** | Chỉ định khoảng cách từ phía phải container đến vị trí neo trên các phần tử con |
| **scroll-padding-top** | Chỉ định khoảng cách từ phía trên container đến vị trí neo trên các phần tử con |
| **scroll-snap-align** | Chỉ định nơi định vị các phần tử khi người dùng ngừng cuộn |
| **scroll-snap-stop** | Chỉ định hành vi cuộn sau khi vuốt nhanh trên bàn di chuột hoặc màn hình cảm ứng |
| **scroll-snap-type** | Chỉ định hành vi neo khi cuộn |
| **scrollbar-color** | Chỉ định màu của thanh cuộn của một phần tử |
| **shape-outside** | Xác định một hình dạng để bọc nội dung nội tuyến |
| **@starting-style** | Xác định kiểu bắt đầu của một phần tử trước khi phần tử nhận được cập nhật kiểu đầu tiên |
| **@supports** | Được sử dụng để kiểm tra liệu trình duyệt có hỗ trợ một tính năng CSS hay không |
| **tab-size** | Chỉ định độ rộng của ký tự tab |
| **table-layout** | Xác định thuật toán được sử dụng để bố trí các ô, hàng và cột của bảng |
| **text-align** | Chỉ định căn chỉnh ngang của văn bản |
| **text-align-last** | Mô tả cách căn chỉnh dòng cuối của một khối hoặc một dòng ngay trước khi ngắt dòng bắt buộc khi `text-align` là "justify" |
| **text-combine-upright** | Chỉ định sự kết hợp của nhiều ký tự vào không gian của một ký tự duy nhất |
| **text-decoration** | Chỉ định trang trí được thêm vào văn bản |
| **text-decoration-color** | Chỉ định màu của trang trí văn bản |
| **text-decoration-line** | Chỉ định loại đường kẻ trong trang trí văn bản |
| **text-decoration-style** | Chỉ định kiểu của đường kẻ trong trang trí văn bản |
| **text-decoration-thickness** | Chỉ định độ dày của đường trang trí |
| **text-emphasis** | Thuộc tính rút gọn cho `text-emphasis-style` và `text-emphasis-color` |
| **text-emphasis-color** | Chỉ định màu của các dấu nhấn |
| **text-emphasis-position** | Chỉ định vị trí của các dấu nhấn |
| **text-emphasis-style** | Chỉ định kiểu của các dấu nhấn |
| **text-indent** | Chỉ định thụt lề của dòng đầu tiên trong một khối văn bản |
| **text-justify** | Chỉ định phương pháp căn chỉnh được sử dụng khi `text-align` là "justify" |
| **text-orientation** | Xác định hướng của các ký tự trong một dòng |
| **text-overflow** | Chỉ định điều gì sẽ xảy ra khi văn bản tràn ra ngoài phần tử chứa |
| **text-shadow** | Thêm bóng cho văn bản |
| **text-transform** | Kiểm soát việc viết hoa của văn bản |
| **text-underline-offset** | Chỉ định khoảng cách dịch chuyển của trang trí gạch dưới văn bản |
| **text-underline-position** | Chỉ định vị trí của trang trí gạch dưới văn bản |
| **top** | Chỉ định vị trí phía trên của một phần tử được định vị |
| **transform** | Áp dụng biến đổi 2D hoặc 3D cho một phần tử |
| **transform-origin** | Cho phép thay đổi vị trí trên các phần tử được biến đổi |
| **transform-style** | Chỉ định cách các phần tử lồng nhau được hiển thị trong không gian 3D |
| **transition** | Thuộc tính rút gọn cho tất cả các thuộc tính `transition-*` |
| **transition-delay** | Chỉ định khi nào hiệu ứng chuyển đổi sẽ bắt đầu |
| **transition-duration** | Chỉ định thời gian (tính bằng giây hoặc mili giây) mà hiệu ứng chuyển đổi cần để hoàn thành |
| **transition-property** | Chỉ định tên của thuộc tính CSS mà hiệu ứng chuyển đổi áp dụng |
| **transition-timing-function** | Chỉ định đường cong tốc độ của hiệu ứng chuyển đổi |
| **translate** | Chỉ định vị trí của một phần tử |
| **unicode-bidi** | Được sử dụng cùng với thuộc tính `direction` để cài đặt hoặc trả về liệu văn bản có nên được ghi đè để hỗ trợ nhiều ngôn ngữ trong cùng một tài liệu hay không |
| **user-select** | Chỉ định liệu văn bản của một phần tử có thể được chọn hay không |
| **vertical-align** | Cài đặt căn chỉnh dọc của một phần tử |
| **visibility** | Chỉ định liệu một phần tử có hiển thị hay không |
| **white-space** | Chỉ định cách xử lý khoảng trắng bên trong một phần tử |
| **widows** | Cài đặt số dòng tối thiểu phải được để lại ở đầu trang hoặc cột |
| **width** | Cài đặt chiều rộng của một phần tử |
| **word-break** | Chỉ định cách các từ nên ngắt khi đến cuối dòng |
| **word-spacing** | Tăng hoặc giảm khoảng cách giữa các từ trong văn bản |
| **word-wrap** | Cho phép các từ dài, không thể ngắt được ngắt và bọc sang dòng tiếp theo |
| **writing-mode** | Chỉ định liệu các dòng văn bản được bố trí theo chiều ngang hay dọc |
| **z-index** | Cài đặt thứ tự xếp chồng của một phần tử được định vị |
| **zoom** | Chỉ định hệ số thu phóng cho một phần tử. Một phần tử có thể được phóng to hoặc thu nhỏ |

Hy vọng bảng trên sẽ giúp bạn dễ dàng tra cứu và hiểu rõ các thuộc tính CSS! Nếu bạn cần thêm giải thích chi tiết về bất kỳ thuộc tính nào, hãy cho tôi biết.
